package com.lyj.lotterdomain.gateway;

import com.lyj.lotterclient.dto.query.ActivityRuleListByParamQuery;
import com.lyj.lotterdomain.activityrule.ActivityRuleEntity;

import java.util.List;

/**
 * @author lyj
 * @date 2023-07-07
 **/
public interface ActivityRuleGateway {
    ActivityRuleEntity save(ActivityRuleEntity entity);

    List<ActivityRuleEntity> list(ActivityRuleListByParamQuery query);

    void deleteByActivityId(Long activityId);
}
