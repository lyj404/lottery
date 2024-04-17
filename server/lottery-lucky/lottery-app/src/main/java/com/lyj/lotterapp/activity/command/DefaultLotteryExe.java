package com.lyj.lotterapp.activity.command;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.WeightRandom;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyj.common.enums.RecordStatusEnum;
import com.lyj.config.exception.BaseException;
import com.lyj.config.utils.AssertUtil;
import com.lyj.config.utils.SecurityUtil;
import com.lyj.lotterapp.assembler.RecordsAssembler;
import com.lyj.lotterapp.context.ActivityLotteryContext;
import com.lyj.lotterclient.dto.RecordsAddCmd;
import com.lyj.lotterclient.dto.data.*;
import com.lyj.lotterclient.dto.query.RecordsListByParamQuery;
import com.lyj.lotterdomain.activity.ActivityEntity;
import com.lyj.lotterdomain.activity.ActivityStatusEnum;
import com.lyj.lotterdomain.activity.ActivityTime;
import com.lyj.lotterdomain.award.AwardEntity;
import com.lyj.lotterdomain.gateway.AwardGateway;
import com.lyj.lotterdomain.gateway.PrizeGateway;
import com.lyj.lotterdomain.gateway.RecordsGateway;
import com.lyj.lotterdomain.records.RecordsEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author lyj
 * @date 2023-07-09
 **/
@Slf4j
@Getter
@Component
@RequiredArgsConstructor
public class DefaultLotteryExe extends BaseLotteryExe {
    private final RecordsGateway recordsGateway;
    private final PrizeGateway prizeGateway;
    private final AwardGateway awardGateway;
    private final TransactionTemplate transactionTemplate;

    /**
     * 添加记录
     *
     * @param context ActivityLotteryContext
     */
    public void addRecord(ActivityLotteryContext context) {
        //插入记录，默认可见
        if (Objects.isNull(context.getIsShow())) {
            context.setIsShow(Boolean.TRUE);
        }
        RecordsAddCmd recordsAddCmd = new RecordsAddCmd();
        recordsAddCmd.setUserId(SecurityUtil.getUserId());
        recordsAddCmd.setActivityId(context.getActivityConfigVO().getActivityVO().getId());
        recordsAddCmd.setActivityName((context.getActivityConfigVO().getActivityVO().getActivityName()));
        recordsAddCmd.setAwardId(context.getAwardVO().getId());
        recordsAddCmd.setIsWinning(Boolean.TRUE.equals(context.getAwardEntity().isPrize()) ? 1 : 0);
        recordsAddCmd.setStatus(context.getIsShow() ? RecordStatusEnum.STATUS_1.getValue() : RecordStatusEnum.STATUS_0.getValue());

        context.setRecordId(recordsGateway.save(RecordsAssembler.toAddEntity(recordsAddCmd)).getId());
    }

    @Override
    protected LotteryResultVO addRecordAndGetLotterResultVO(ActivityLotteryContext context) {
        LotteryResultVO lotteryResultVO = transactionTemplate.execute(status -> {
            LotteryResultVO resultVO = null;
            try {
                addRecord(context);
                resultVO = getLotteryResultVO(context.getAwardEntity());
            } catch (Exception e) {
                status.setRollbackOnly();
                log.error("插入抽奖记录或封装抽奖结果失败！", e);
            }
            return resultVO;
        });
        AssertUtil.isTrue(Objects.isNull(lotteryResultVO), "抱歉访问人数过多请稍微再来");
        return lotteryResultVO;
    }

    @Override
    protected Boolean lotteryBefore(ActivityLotteryContext context) {
        return transactionTemplate.execute(status -> {
            Boolean success = Boolean.TRUE;
            try {
                //扣减数据库
                AssertUtil.isTrue(awardGateway.reduceAwardNumber(context.getAwardVO().getId(), 1) != 1,
                        "扣减库存失败！");
                //添加记录，可见记录
                addRecord(context);
            } catch (Exception e) {
                //错误处理
                status.setRollbackOnly();
                //回退库存
                awardGateway.reduceAwardNumber(context.getAwardVO().getId(), -1);
                log.error("扣减库存和插入记录出错:", e);
                success = Boolean.FALSE;
            }
            return success;
        });
    }

    /**
     * 构建结果集
     *
     * @param awardEntity AwardEntity
     * @return LotteryResultVO
     */
    @Override
    protected LotteryResultVO getLotteryResultVO(AwardEntity awardEntity) {
        LotteryResultVO resultVO = new LotteryResultVO();
        resultVO.setAwardName(awardEntity.getAwardName());
        if (Objects.nonNull(awardEntity.getPrizeId()) && !awardEntity.getPrizeId().toString().equals("0")) {
            resultVO.setPrizeName(prizeGateway.one(awardEntity.getPrizeId()).getPrizeName());
        }
        resultVO.setIsLottery(awardEntity.isPrize());
        return resultVO;
    }

    /**
     * 抽奖
     *
     * @param awardVOList List<AwardVO>
     * @return AwardVO
     */
    @Override
    protected AwardVO getAward(List<AwardVO> awardVOList) {
        //根据权重随机抽奖
        List<WeightRandom.WeightObj<AwardVO>> weightObjList = new ArrayList<>();
        awardVOList.forEach(item -> weightObjList.add(new WeightRandom.WeightObj<>(item, item.getProbability())));
        //创建带有权重的随机生成器
        WeightRandom<AwardVO> weightRandom = RandomUtil.weightRandom(weightObjList);
        return weightRandom.next();
    }

    /**
     * 校验活动规则
     *
     * @param activityConfigVO ActivityConfigVO
     */
    @Override
    protected void checkActivityRule(ActivityConfigVO activityConfigVO) {
        List<RuleVO> ruleVOList = activityConfigVO.getRuleVOList();
        if (CollUtil.isEmpty(ruleVOList)) {
            return;
        }
        //获取活动第一个规则
        RuleVO ruleVO = ruleVOList.get(0);
        //查询记录
        final var query = new RecordsListByParamQuery();
        query.setUserId(SecurityUtil.getUserId());
        query.setActivityId(activityConfigVO.getActivityVO().getId());
        query.setPageSize(1000);
        IPage<RecordsEntity> page = recordsGateway.page(query);
        //校验最大参与次数
        AssertUtil.isTrue(page.getRecords().size() >= ruleVO.getMaxJoinNumber(), "达到活动最大参与次数，现不可参与活动");
        List<RecordsEntity> winningRecordList = page.getRecords()
                .stream()
                .filter(item -> item.getIsWinning() == 1)
                .toList();
        //校验最大中将次数
        AssertUtil.isTrue(winningRecordList.size() >= ruleVO.getMaxWinningNumber(), "你已达到最大中将次数，不可参与");
    }

    /**
     * 活动时间校验
     *
     * @param activityVO ActivityVO
     */
    @Override
    protected void checkActivityTime(ActivityVO activityVO) {
        ActivityEntity entity = new ActivityEntity();
        entity.setActivityTime(new ActivityTime(activityVO.getStartTime(), activityVO.getEndTime()));
        ActivityStatusEnum status = entity.getActivityTime().getStatus();
        if (!ActivityStatusEnum.START.equals(status)) {
            throw new BaseException(String.format("活动%s", status.getDescription()));
        }
    }
}
