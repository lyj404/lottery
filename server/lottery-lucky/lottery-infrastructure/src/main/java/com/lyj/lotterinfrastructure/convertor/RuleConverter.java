package com.lyj.lotterinfrastructure.convertor;

import com.lyj.lotterdomain.rule.MinNumber;
import com.lyj.lotterdomain.rule.RuleEntity;
import com.lyj.lotterinfrastructure.gateway.impl.dataobject.RuleDB;

import java.time.LocalDateTime;

/**
 * @author lyj
 * @date 2023-07-05
 **/
public class RuleConverter {
    private RuleConverter(){}

    public static RuleDB toRuleDB(RuleEntity entity) {
        RuleDB ruleDB = new RuleDB();
        ruleDB.setId(entity.getId());
        ruleDB.setRuleName(entity.getRuleName());
        ruleDB.setMaxJoinNumber(entity.getMaxJoinNumber().getNumber());
        ruleDB.setMaxWinningNumber(entity.getMaxWinningNumber().getNumber());
        ruleDB.setCreateTime(LocalDateTime.now());
        ruleDB.setUpdateTime(LocalDateTime.now());

        return ruleDB;
    }

    public static RuleEntity toRuleEntity(RuleDB ruleDB) {
        RuleEntity ruleEntity = new RuleEntity();
        ruleEntity.setId(ruleDB.getId());
        ruleEntity.setRuleName(ruleDB.getRuleName());
        ruleEntity.setMaxJoinNumber(new MinNumber(ruleDB.getMaxJoinNumber()));
        ruleEntity.setMaxWinningNumber(new MinNumber(ruleDB.getMaxWinningNumber()));
        ruleEntity.setCreateTime(ruleDB.getCreateTime());
        ruleEntity.setUpdateTime(ruleDB.getUpdateTime());

        return ruleEntity;
    }
}
