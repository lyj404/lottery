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
 * 规则表
 *
 * @TableName rule
 */
@TableName(value = "rule")
@Data
public class RuleDB implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 规则名称
     */
    @TableField(value = "rule_name")
    private String ruleName;

    /**
     * 最大可参与次数
     */
    @TableField(value = "max_join_number")
    private Integer maxJoinNumber;

    /**
     * 最大可中将次数
     */
    @TableField(value = "max_winning_number")
    private Integer maxWinningNumber;

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