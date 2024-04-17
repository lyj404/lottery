package com.lyj.lotterapp.context;

import com.lyj.lotterclient.dto.data.ActivityConfigVO;
import com.lyj.lotterclient.dto.data.AwardVO;
import com.lyj.lotterdomain.award.AwardEntity;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author lyj
 * @date 2023-07-10
 **/
@Data
@Accessors(chain = true)
public class ActivityLotteryContext {
    private ActivityConfigVO activityConfigVO;
    /**
     * 抽奖算法获得的奖项entity
     */
    private AwardEntity awardEntity;
    /**
     * 抽奖算法获得的奖项
     */
    private AwardVO awardVO;

    /**
     * 是否中将
     */
    private Boolean isWinTheLottery;

    /**
     * 是否可见，用户中将日志
     */
    private Boolean isShow;

    /**
     * 中将记录id
     */
    private Long recordId;
}