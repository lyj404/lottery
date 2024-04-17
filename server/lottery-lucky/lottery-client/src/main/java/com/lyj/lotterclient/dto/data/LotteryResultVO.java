package com.lyj.lotterclient.dto.data;

import lombok.Data;

/**
 * @author lyj
 * @date 2023-07-09
 **/
@Data
public class LotteryResultVO {
    /**
     * 奖品名称
     */
    private String awardName;

    /**
     * 奖项名称
     */
    private String prizeName;

    /**
     * 是否中将
     */
    private Boolean isLottery;
}
