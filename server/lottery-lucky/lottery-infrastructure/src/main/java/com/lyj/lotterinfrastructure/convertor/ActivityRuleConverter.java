package com.lyj.lotterinfrastructure.convertor;

import com.lyj.lotterdomain.activityrule.ActivityRuleEntity;
import com.lyj.lotterinfrastructure.gateway.impl.dataobject.ActivityRuleDB;

/**
 * @author lyj
 * @date 2023-07-07
 **/
public class ActivityRuleConverter {
    private ActivityRuleConverter(){}

    public static ActivityRuleDB toActivityRuleDB(ActivityRuleEntity entity) {
        ActivityRuleDB activityRuleDB = new ActivityRuleDB();
        activityRuleDB.setId(entity.getId());
        activityRuleDB.setActivityId(entity.getActivityId());
        activityRuleDB.setRuleId(entity.getRuleId());
        activityRuleDB.setCreateTime(entity.getCreateTime());
        activityRuleDB.setUpdateTime(entity.getUpdateTime());

        return activityRuleDB;
    }

    public static ActivityRuleEntity toActivityRuleEntity(ActivityRuleDB activityRuleDB) {
        ActivityRuleEntity activityRuleEntity = new ActivityRuleEntity();
        activityRuleEntity.setId(activityRuleDB.getId());
        activityRuleEntity.setActivityId(activityRuleDB.getActivityId());
        activityRuleEntity.setRuleId(activityRuleDB.getRuleId());
        activityRuleEntity.setCreateTime(activityRuleDB.getCreateTime());
        activityRuleEntity.setUpdateTime(activityRuleDB.getUpdateTime());

        return activityRuleEntity;
    }
}
