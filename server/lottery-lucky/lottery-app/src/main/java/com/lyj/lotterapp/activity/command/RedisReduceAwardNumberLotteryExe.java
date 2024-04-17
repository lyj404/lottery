package com.lyj.lotterapp.activity.command;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyj.common.enums.RecordStatusEnum;
import com.lyj.config.exception.BaseException;
import com.lyj.config.utils.AssertUtil;
import com.lyj.config.utils.FileLoadUtil;
import com.lyj.lotterapp.context.ActivityLotteryContext;
import com.lyj.lotterapp.listener.AwardInventoryToRedisApplicationListener;
import com.lyj.lotterapp.mq.product.ActivityLotteryMessageProduct;
import com.lyj.lotterclient.dto.query.RecordsListByParamQuery;
import com.lyj.lotterdomain.gateway.AwardGateway;
import com.lyj.lotterdomain.gateway.PrizeGateway;
import com.lyj.lotterdomain.gateway.RecordsGateway;
import com.lyj.lotterdomain.records.RecordsEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @author lyj
 * @date 2023-07-10
 **/
@Slf4j
@Component
public class RedisReduceAwardNumberLotteryExe extends DefaultLotteryExe {
    private final RedisTemplate<String, Object> redisTemplate;
    private final ActivityLotteryMessageProduct lotteryMessageProduct;
    private static String InventoryReduceLua;
    private static String InventoryRollbackLua;

    static {
        InventoryReduceLua = FileLoadUtil.read("lua/Inventory_Reduce.lua");
        InventoryRollbackLua = FileLoadUtil.read("lua/Inventory_rollback.lua");
    }

    public RedisReduceAwardNumberLotteryExe(RecordsGateway recordsGateway,
                                            PrizeGateway prizeGateway,
                                            AwardGateway awardGateway,
                                            TransactionTemplate transactionTemplate,
                                            RedisTemplate<String, Object> redisTemplate,
                                            ActivityLotteryMessageProduct lotteryMessageProduct) {
        super(recordsGateway, prizeGateway, awardGateway, transactionTemplate);
        this.redisTemplate = redisTemplate;
        this.lotteryMessageProduct = lotteryMessageProduct;
    }

    @Override
    protected Boolean lotteryBefore(ActivityLotteryContext context) {
        //扣减redis库存
        Integer reduceLua = invokeInventoryReduceLua(AwardInventoryToRedisApplicationListener.getKey(
                context.getAwardEntity().getActivityId(),
                context.getAwardVO().getId()
        ));
        if (reduceLua != 1) {
            return Boolean.FALSE;
        }
        return super.getTransactionTemplate().execute(status -> {
            Boolean success = Boolean.TRUE;
            try {
                //插入不可见抽奖记录
                context.setIsShow(Boolean.FALSE);
                super.addRecord(context);
                //发送MQ消息
                if (Boolean.FALSE.equals(lotteryMessageProduct.send(context))) {
                    throw new BaseException("MQ发送消息失败");
                }
            } catch (Exception e) {
                //出现错误，回滚
                status.isRollbackOnly();
                invokeInventoryRollback(AwardInventoryToRedisApplicationListener.getKey(
                        context.getAwardEntity().getActivityId(),
                        context.getAwardVO().getId()
                ));
                success = Boolean.FALSE;
                log.error("发送MQ消息失败或者扣减库存失败", e);
            }
            return success;
        });
    }

    public Integer invokeInventoryReduceLua(String key) {
        //lua脚本返回执行后的库存
        RedisScript<Long> redisScript = new DefaultRedisScript<>(InventoryReduceLua, Long.class);
        Long execute = redisTemplate.opsForValue().getOperations().execute(
                redisScript,
                List.of(key));
        if (Objects.isNull(execute) || execute == -1) {
            return 0;
        }
        return 1;
    }

    public Integer invokeInventoryRollback(String key) {
        //lua脚本返回执行后的库存
        RedisScript<Long> redisScript = new DefaultRedisScript<>(InventoryRollbackLua, Long.class);
        Long execute = redisTemplate.opsForValue().getOperations().execute(
                redisScript,
                List.of(key));
        if (Objects.isNull(execute) || execute < 0) {
            return 0;
        }
        return 1;
    }

    /**
     * MQ执行：扣减库存并修改不可见中将记录状态
     *
     * @param context ActivityLotteryContext
     * @return Boolean
     */
    public Boolean mqReduceOfInventoryAndUpdateRecordStatus(ActivityLotteryContext context) {
        return reduceOfInventoryAndUpdateRecordStatus(context.getAwardVO().getId(), context.getRecordId());
    }

    /**
     * 扣减库存并修改不可见中将记录状态
     *
     * @param awardId Long
     * @return Boolean
     */
    public Boolean reduceOfInventoryAndUpdateRecordStatus(Long awardId, Long recordId) {
        return super.getTransactionTemplate().execute(status -> {
            Boolean success = Boolean.TRUE;
            try {
                //扣减库存
                int i = super.getAwardGateway().reduceAwardNumber(awardId, -1);
                AssertUtil.isTrue(i != 1, "扣减库存失败");
                //修改不可见中将记录状态
                Boolean update = super.getRecordsGateway().update(recordId, RecordStatusEnum.STATUS_1.getValue());
                AssertUtil.isFalse(update, "修改记录状态失败");
            } catch (Exception e) {
                log.error("执行扣减库存或修改不可见中将记录状态出错", e);
                status.setRollbackOnly();
                success = Boolean.FALSE;
            }
            return success;
        });
    }

    /**
     * 定时任务执行：扣除库存并修改不可见记录状态
     */
    public void scheduleExecute() {
        LocalDateTime dateTime = LocalDateTime.now().plusMinutes(-5);
        final var query = new RecordsListByParamQuery();
        query.setStatus(RecordStatusEnum.STATUS_0.getValue());
        query.setPageSize(100);
        IPage<RecordsEntity> page = super.getRecordsGateway().page(query);
        for (var record : page.getRecords()) {
            if (record.getCreateTime().isAfter(dateTime)) {
                continue;
            }
            reduceOfInventoryAndUpdateRecordStatus(record.getAwardId(), record.getId());
        }
    }
}
