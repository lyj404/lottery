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
 * 抽奖记录表
 *
 * @TableName records
 */
@TableName(value = "records")
@Data
public class RecordsDB implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 活动id
     */
    @TableField(value = "activity_id")
    private Long activityId;

    /**
     * 活动名称
     */
    @TableField(value = "activity_name")
    private String activityName;

    /**
     * 奖项id
     */
    @TableField(value = "award_id")
    private Long awardId;

    /**
     * 奖项名称
     */
    @TableField(exist = false)
    private String awardName;

    /**
     * 奖品名称
     */
    @TableField(exist = false)
    private String prizeName;

    /**
     * 奖品类型
     */
    @TableField(exist = false)
    private Integer prizeType;

    /**
     * 是否中将，0-未中将，1-中将
     */
    @TableField(value = "is_winning")
    private Integer isWinning;

    /**
     * 状态，0，1，2，3
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 创建世家
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