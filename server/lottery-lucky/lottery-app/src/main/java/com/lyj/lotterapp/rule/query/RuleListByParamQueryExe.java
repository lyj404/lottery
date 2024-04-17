package com.lyj.lotterapp.rule.query;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyj.lotterapp.assembler.RuleAssembler;
import com.lyj.lotterclient.dto.data.RuleVO;
import com.lyj.lotterclient.dto.query.RuleListByParamQuery;
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
public class RuleListByParamQueryExe {
    private final RuleGateway ruleGateway;

    public IPage<RuleVO> execute(RuleListByParamQuery query) {
        IPage<RuleEntity> page = ruleGateway.page(query);
        return page.convert(RuleAssembler::toRuleVO);
    }
}
