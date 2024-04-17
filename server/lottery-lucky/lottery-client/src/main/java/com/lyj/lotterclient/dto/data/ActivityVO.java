package com.lyj.lotterclient.dto.data;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lyj
 * @date 2023-07-07
 **/
@Data
public class ActivityVO {
    /**
     * 主键
     */
    private Long id;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 活动开始时间
     */
    private LocalDateTime startTime;

    /**
     * 活动结束时间
     */
    private LocalDateTime endTime;

    /**
     * 活动状态
     */
    private Integer status;

    /**
     * 活动描述
     */
    private String activityDescription;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
}
