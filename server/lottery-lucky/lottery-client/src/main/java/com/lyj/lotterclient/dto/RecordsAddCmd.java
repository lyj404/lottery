package com.lyj.lotterclient.dto;

import com.alibaba.cola.dto.Command;
import lombok.Data;

/**
 * @author lyj
 * @date 2023-07-12
 **/
@Data
public class RecordsAddCmd extends Command {
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 活动id
     */
    private Long activityId;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 奖项id
     */
    private Long awardId;


    /**
     * 是否中将，0-未中将，1-中将
     */
    private Integer isWinning;

    /**
     * 状态，0，1，2，3
     */
    private Integer status;
}
