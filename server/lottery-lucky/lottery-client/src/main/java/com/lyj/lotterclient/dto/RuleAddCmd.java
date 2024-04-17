package com.lyj.lotterclient.dto;

import com.alibaba.cola.dto.Command;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author lyj
 * @date 2023-07-05
 **/
@Data
public class RuleAddCmd extends Command {
    /**
     * 规则名称
     */
    @NotNull(message = "规则名称不能为空")
    private String ruleName;

    /**
     * 最大可参与次数
     */
    @NotNull(message = "最大可参与次数不能为空")
    private Integer maxJoinNumber;

    /**
     * 最大可中将次数
     */
    @NotNull(message = "最大可中将次数不能为空")
    private Integer maxWinningNumber;
}
