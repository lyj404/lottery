package com.lyj.lotterdomain.activityrule;

import com.alibaba.cola.domain.Entity;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lyj
 * @date 2023-07-07
 **/
@Entity
@Data
public class ActivityRuleEntity {
    /**
     * 主键
     */
    private Long id;

    /**
     * 活动id
     */
    private Long activityId;

    /**
     * 规则id
     */
    private Long ruleId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
}