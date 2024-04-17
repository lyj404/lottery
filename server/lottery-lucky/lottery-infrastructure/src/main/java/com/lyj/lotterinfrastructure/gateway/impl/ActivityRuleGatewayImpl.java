package com.lyj.lotterinfrastructure.gateway.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyj.config.enums.ExceptionEnum;
import com.lyj.config.utils.AssertUtil;
import com.lyj.lotterclient.dto.query.ActivityRuleListByParamQuery;
import com.lyj.lotterdomain.activityrule.ActivityRuleEntity;
import com.lyj.lotterdomain.gateway.ActivityRuleGateway;
import com.lyj.lotterinfrastructure.convertor.ActivityRuleConverter;
import com.lyj.lotterinfrastructure.gateway.impl.dataobject.ActivityRuleDB;
import com.lyj.lotterinfrastructure.gateway.impl.mapper.ActivityRuleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lyj
 * @date 2023-07-07
 **/
@Component
@RequiredArgsConstructor
public class ActivityRuleGatewayImpl implements ActivityRuleGateway {
    private final ActivityRuleMapper activityRuleMapper;

    @Override
    public ActivityRuleEntity save(ActivityRuleEntity entity) {
        ActivityRuleDB activityRuleDB = ActivityRuleConverter.toActivityRuleDB(entity);
        AssertUtil.isTrue(activityRuleMapper.insert(activityRuleDB) <= 0, ExceptionEnum.ADD_ERROR.getDescription());
        return ActivityRuleConverter.toActivityRuleEntity(activityRuleDB);
    }

    @Override
    public List<ActivityRuleEntity> list(ActivityRuleListByParamQuery query) {
        List<ActivityRuleDB> list = activityRuleMapper.list(query);
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        }
        return new Page<ActivityRuleDB>()
                .setRecords(list)
                .convert(ActivityRuleConverter::toActivityRuleEntity)
                .getRecords();
    }

    @Override
    public void deleteByActivityId(Long activityId) {
        activityRuleMapper.deleteByActivityId(activityId);
    }
}
