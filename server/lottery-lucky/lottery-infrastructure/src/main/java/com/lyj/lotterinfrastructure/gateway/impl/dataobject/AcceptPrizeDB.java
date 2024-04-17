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
 * 领奖表
 *
 * @TableName accept_prize
 */
@TableName(value = "accept_prize")
@Data
public class AcceptPrizeDB implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 抽奖记录id
     */
    @TableField(value = "record_id")
    private Long recordId;

    /**
     * 电话
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 地址
     */
    @TableField(value = "address")
    private String address;

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