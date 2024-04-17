package com.lyj.lotterinfrastructure.gateway.impl.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 奖品表
 *
 * @TableName prize
 */
@TableName(value = "prize")
@Data
public class PrizeDB implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 奖品名称
     */
    @TableField(value = "prize_name")
    private String prizeName;

    /**
     * 库存
     */
    @TableField(value = "inventory")
    private Integer inventory;

    /**
     * 金额
     */
    @TableField(value = "money")
    private BigDecimal money;

    /**
     * 类型，1-商品，2-金钱
     */
    @TableField(value = "type")
    private Integer type;

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