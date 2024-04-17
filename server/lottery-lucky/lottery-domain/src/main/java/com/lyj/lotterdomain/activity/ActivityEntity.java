package com.lyj.lotterdomain.activity;

import com.alibaba.cola.domain.Entity;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lyj
 * @date 2023-07-07
 **/
@Entity
@Data
public class ActivityEntity {
    /**
     * 主键
     */
    private Long id;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 活动时间
     */
    private ActivityTime activityTime;

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
