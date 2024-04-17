package com.lyj.lotterinfrastructure.gateway.impl.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 活动表
 *
 * @TableName activity
 */
@TableName(value = "activity")
@Data
public class ActivityDB implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 活动名称
     */
    @TableField(value = "activity_name")
    private String activityName;

    /**
     * 活动开始时间
     */
    @TableField(value = "start_time")
    private LocalDateTime startTime;

    /**
     * 活动结束时间
     */
    @TableField(value = "end_time")
    private LocalDateTime endTime;

    /**
     * 活动描述
     */
    @TableField(value = "activity_description")
    private String activityDescription;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}