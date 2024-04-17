package com.lyj.lotterclient.dto;

import com.alibaba.cola.dto.Command;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author lyj
 * @date 2023-07-06
 **/
@Data
public class AwardAddCmd extends Command {
    /**
     * 奖品id
     */
    @NotNull(message = "奖品id不能为空")
    private Long prizeId;

    /**
     * 活动id
     */
    private Long activityId;

    /**
     * 奖项数量
     */
    @NotNull(message = "奖项数量不能为空")
    private Integer number;

    /**
     * 奖项名称
     */
    @NotNull(message = "奖项名称不能为空")
    private String awardName;

    /**
     * 概率
     */
    @NotNull(message = "概率不能为空")
    private Double probability;
}