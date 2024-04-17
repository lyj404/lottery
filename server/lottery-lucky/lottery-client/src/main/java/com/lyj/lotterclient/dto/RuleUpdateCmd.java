package com.lyj.lotterclient.dto;

import com.alibaba.cola.dto.Command;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author lyj
 * @date 2023-07-05
 **/
@Data
public class RuleUpdateCmd extends Command {
    /**
     * 主键
     */
    @NotNull(message = "不能为空")
    private Long id;

    /**
     * 规则名称
     */
    private String ruleName;

    /**
     * 最大可参与次数
     */
    private Integer maxJoinNumber;

    /**
     * 最大可中将次数
     */
    private Integer maxWinningNumber;
}
