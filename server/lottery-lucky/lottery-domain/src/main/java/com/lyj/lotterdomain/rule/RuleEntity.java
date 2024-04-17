package com.lyj.lotterdomain.rule;

import com.alibaba.cola.domain.Entity;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lyj
 * @date 2023-07-05
 **/
@Data
@Entity
public class RuleEntity {
    /**
     * 主键
     */
    private Long id;

    /**
     * 规则名称
     */
    private String ruleName;

    /**
     * 最大可参与次数
     */
    private MinNumber maxJoinNumber;

    /**
     * 最大可中将次数
     */
    private MinNumber maxWinningNumber;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
}
