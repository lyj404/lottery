package com.lyj.lotterdomain.gateway;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyj.lotterclient.dto.query.RuleListByParamQuery;
import com.lyj.lotterdomain.rule.RuleEntity;

/**
 * @author lyj
 * @date 2023-07-05
 **/
public interface RuleGateway {
    RuleEntity save(RuleEntity entity);

    IPage<RuleEntity> page(RuleListByParamQuery query);
}
