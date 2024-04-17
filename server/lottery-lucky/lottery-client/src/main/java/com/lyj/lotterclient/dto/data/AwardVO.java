package com.lyj.lotterclient.dto.data;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lyj
 * @date 2023-07-05
 **/
@Data
public class AwardVO {
    /**
     * 主键
     */
    private Long id;

    /**
     * 奖品id
     */
    private Long prizeId;

    /**
     * 奖品名称
     */
    private String prizeName;

    /**
     * 活动id
     */
    private Long activityId;

    /**
     * 奖项数量
     */
    private Integer number;

    /**
     * 奖项名称
     */
    private String awardName;


    /**
     * 概率
     */
    private Double probability;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
}
