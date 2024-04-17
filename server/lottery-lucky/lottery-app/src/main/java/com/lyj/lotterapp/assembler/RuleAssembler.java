package com.lyj.lotterapp.assembler;

import com.lyj.lotterclient.dto.RuleAddCmd;
import com.lyj.lotterclient.dto.RuleUpdateCmd;
import com.lyj.lotterclient.dto.data.RuleVO;
import com.lyj.lotterdomain.rule.MinNumber;
import com.lyj.lotterdomain.rule.RuleEntity;

import java.time.LocalDateTime;

/**
 * @author lyj
 * @date 2023-07-05
 **/
public class RuleAssembler {
    private RuleAssembler(){}
    public static RuleVO toRuleVO(RuleEntity entity) {
        RuleVO ruleVO = new RuleVO();
        ruleVO.setId(entity.getId());
        ruleVO.setRuleName(entity.getRuleName());
        ruleVO.setMaxJoinNumber(entity.getMaxJoinNumber().getNumber());
        ruleVO.setMaxWinningNumber(entity.getMaxWinningNumber().getNumber());
        ruleVO.setCreateTime(entity.getCreateTime());
        ruleVO.setUpdateTime(entity.getUpdateTime());

        return ruleVO;
    }

    public static RuleEntity toAddEntity(RuleAddCmd cmd) {
        RuleEntity ruleEntity = new RuleEntity();
        ruleEntity.setRuleName(cmd.getRuleName());
        ruleEntity.setMaxJoinNumber(new MinNumber(cmd.getMaxJoinNumber()));
        ruleEntity.setMaxWinningNumber(new MinNumber(cmd.getMaxWinningNumber()));
        ruleEntity.setCreateTime(LocalDateTime.now());
        ruleEntity.setUpdateTime(LocalDateTime.now());

        return ruleEntity;
    }

    public static RuleEntity toUpdateEntity(RuleUpdateCmd cmd) {
        RuleEntity ruleEntity = new RuleEntity();
        ruleEntity.setId(cmd.getId());
        ruleEntity.setRuleName(cmd.getRuleName());
        ruleEntity.setMaxJoinNumber(new MinNumber(cmd.getMaxJoinNumber()));
        ruleEntity.setMaxWinningNumber(new MinNumber(cmd.getMaxWinningNumber()));
        ruleEntity.setUpdateTime(LocalDateTime.now());

        return ruleEntity;
    }
}
