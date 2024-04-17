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
 * 奖项表
 *
 * @TableName award
 */
@TableName(value = "award")
@Data
public class AwardDB implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 奖品id
     */
    @TableField(value = "prize_id")
    private Long prizeId;

    /**
     * 奖品名称
     */
    @TableField(exist = false)
    private String prizeName;

    /**
     * 活动id
     */
    @TableField(value = "activity_id")
    private Long activityId;

    /**
     * 奖项数量
     */
    @TableField(value = "number")
    private Integer number;

    /**
     * 奖项名称
     */
    @TableField(value = "award_name")
    private String awardName;

    /**
     * 概率
     */
    @TableField(value = "probability")
    private Double probability;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    /**
     * 修改世家
     */
    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}