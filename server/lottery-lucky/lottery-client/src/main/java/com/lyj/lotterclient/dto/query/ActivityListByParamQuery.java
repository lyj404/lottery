package com.lyj.lotterclient.dto.query;

import com.alibaba.cola.dto.PageQuery;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lyj
 * @date 2023-07-07
 **/
@Data
public class ActivityListByParamQuery extends PageQuery {
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
}
