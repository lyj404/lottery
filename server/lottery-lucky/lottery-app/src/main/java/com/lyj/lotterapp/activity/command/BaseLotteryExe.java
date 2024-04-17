package com.lyj.lotterapp.activity.command;

import com.lyj.lotterapp.assembler.AwardAssembler;
import com.lyj.lotterapp.context.ActivityLotteryContext;
import com.lyj.lotterclient.dto.data.ActivityConfigVO;
import com.lyj.lotterclient.dto.data.ActivityVO;
import com.lyj.lotterclient.dto.data.AwardVO;
import com.lyj.lotterclient.dto.data.LotteryResultVO;
import com.lyj.lotterdomain.award.AwardEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author lyj
 * @date 2023-07-09
 **/
@Slf4j
public abstract class BaseLotteryExe {
    /**
     * 抽奖流程
     *
     * @param context ActivityLotteryContext
     * @return LotteryResultVO
     */
    public LotteryResultVO execute(ActivityLotteryContext context) {
        //校验活动时间
        checkActivityTime(context.getActivityConfigVO().getActivityVO());
        //校验活动规则
        checkActivityRule(context.getActivityConfigVO());
        //剔除奖项库存为空的项
        List<AwardVO> awardVOList = removeAwardInventoryNull(context.getActivityConfigVO().getAwardVOList());
        //调用抽奖算法进行抽奖
        context.setAwardVO(getAward(awardVOList));
        context.setAwardEntity(AwardAssembler.toAwardEntity(context.getAwardVO()));
        context.setIsWinTheLottery(context.getAwardEntity().isPrize());

        if (Boolean.FALSE.equals(context.getIsWinTheLottery())) {
            //插入未中奖记录
            return addRecordAndGetLotterResultVO(context);
        }

        Boolean before;
        try {
            //调用抽奖后续流程
            before = lotteryBefore(context);
        } catch (Exception e) {
            before = Boolean.FALSE;
            log.error("执行lotteryBefore出错，默认返回未中奖信息", e);
        }
        if (Boolean.FALSE.equals(before)) {
            //执行lotteryBefore出错，默认返回未中奖
            context.setAwardVO(getNotAward(context.getActivityConfigVO().getAwardVOList()));
            context.setAwardEntity(AwardAssembler.toAwardEntity(context.getAwardVO()));
            context.setIsWinTheLottery(Boolean.FALSE);
            return addRecordAndGetLotterResultVO(context);
        }
        //返回结果
        return getLotteryResultVO(context.getAwardEntity());
    }

    protected abstract LotteryResultVO addRecordAndGetLotterResultVO(ActivityLotteryContext context);

    protected abstract Boolean lotteryBefore(ActivityLotteryContext context);

    protected abstract void addRecord(ActivityLotteryContext context);

    protected abstract void checkActivityRule(ActivityConfigVO activityConfigVO);

    /**
     * 校验活动
     *
     * @param activityVO ActivityVO
     */
    protected abstract void checkActivityTime(ActivityVO activityVO);

    protected abstract LotteryResultVO getLotteryResultVO(AwardEntity awardEntity);

    protected abstract AwardVO getAward(List<AwardVO> awardVOList);

    private AwardVO getNotAward(List<AwardVO> awardVOList) {
        for (var awardVO : awardVOList) {
            if ("0".equals(awardVO.getPrizeId().toString())) {
                return awardVO;
            }
        }
        return null;
    }

    /**
     * 剔除奖项库存为空的项
     *
     * @param awardVOList List<AwardVO>
     * @return List<AwardVO>
     */
    private List<AwardVO> removeAwardInventoryNull(List<AwardVO> awardVOList) {
        if (CollectionUtils.isEmpty(awardVOList)) {
            return new ArrayList<>();
        }
        return awardVOList.stream()
                .filter(item -> item.getNumber() > 0 || "0".equals(item.getPrizeId().toString())).toList();
    }
}
