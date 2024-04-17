package com.lyj.lotterinfrastructure.gateway.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyj.config.enums.ExceptionEnum;
import com.lyj.config.utils.AssertUtil;
import com.lyj.lotterclient.dto.query.RuleListByParamQuery;
import com.lyj.lotterdomain.gateway.RuleGateway;
import com.lyj.lotterdomain.rule.RuleEntity;
import com.lyj.lotterinfrastructure.convertor.RuleConverter;
import com.lyj.lotterinfrastructure.gateway.impl.dataobject.RuleDB;
import com.lyj.lotterinfrastructure.gateway.impl.mapper.RuleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author lyj
 * @date 2023-07-05
 **/
@Component
@RequiredArgsConstructor
public class RuleGatewayImpl implements RuleGateway {
    private final RuleMapper ruleMapper;

    @Override
    public RuleEntity save(RuleEntity entity) {
        if (Objects.isNull(entity.getId())) {
            return addRule(entity);
        }
        return updateRule(entity);
    }

    private RuleEntity updateRule(RuleEntity entity) {
        RuleDB ruleDB = RuleConverter.toRuleDB(entity);
        AssertUtil.isTrue(ruleMapper.updateById(ruleDB) <= 0, ExceptionEnum.UPDATE_ERROR.getDescription());
        return RuleConverter.toRuleEntity(ruleDB);
    }

    private RuleEntity addRule(RuleEntity entity) {
        RuleDB ruleDB = RuleConverter.toRuleDB(entity);
        AssertUtil.isTrue(ruleMapper.insert(ruleDB) <= 0, ExceptionEnum.ADD_ERROR.getDescription());
        return RuleConverter.toRuleEntity(ruleDB);
    }

    @Override
    public IPage<RuleEntity> page(RuleListByParamQuery query) {
        IPage<RuleDB> page = ruleMapper.page(new Page<>(query.getPageIndex(), query.getPageSize()), query);
        return page.convert(RuleConverter::toRuleEntity);
    }
}
