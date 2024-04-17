package com.lyj.lotterclient.dto.data;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lyj
 * @date 2023-07-11
 **/
@Data
public class RecordsVO {
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
     * 奖项名称
     */
    private String awardName;

    /**
     * 奖品名称
     */
    private String prizeName;

    /**
     * 奖品类型
     */
    private Integer prizeType;

    /**
     * 是否中将，0-未中将，1-中将
     */
    private Integer isWinning;

    /**
     * 状态，0，1，2，3
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
}
