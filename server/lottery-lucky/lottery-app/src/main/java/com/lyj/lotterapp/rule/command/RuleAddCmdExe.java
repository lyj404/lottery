package com.lyj.lotterapp.rule.command;

import com.lyj.lotterapp.assembler.RuleAssembler;
import com.lyj.lotterclient.dto.RuleAddCmd;
import com.lyj.lotterclient.dto.data.RuleVO;
import com.lyj.lotterdomain.gateway.RuleGateway;
import com.lyj.lotterdomain.rule.RuleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author lyj
 * @date 2023-07-05
 **/
@Component
@RequiredArgsConstructor
public class RuleAddCmdExe {
    private final RuleGateway ruleGateway;

    public RuleVO execute(RuleAddCmd cmd) {
        RuleEntity save = ruleGateway.save(RuleAssembler.toAddEntity(cmd));
        return RuleAssembler.toRuleVO(save);
    }
}
