package com.lyj.lotterinfrastructure.convertor;

import com.lyj.lotterdomain.activity.ActivityEntity;
import com.lyj.lotterdomain.activity.ActivityTime;
import com.lyj.lotterinfrastructure.gateway.impl.dataobject.ActivityDB;

/**
 * @author lyj
 * @date 2023-07-07
 **/
public class ActivityConverter {
    private ActivityConverter(){}

    public static ActivityDB toActivityDB(ActivityEntity entity) {
        ActivityDB activityDB = new ActivityDB();
        activityDB.setId(entity.getId());
        activityDB.setActivityName(entity.getActivityName());
        activityDB.setStartTime(entity.getActivityTime().getStartTime());
        activityDB.setEndTime(entity.getActivityTime().getEndTime());
        activityDB.setActivityDescription(entity.getActivityDescription());
        activityDB.setCreateTime(entity.getCreateTime());
        activityDB.setUpdateTime(entity.getUpdateTime());

        return activityDB;
    }

    public static ActivityEntity toActivityEntity(ActivityDB activityDB) {
        ActivityEntity entity = new ActivityEntity();
        entity.setId(activityDB.getId());
        entity.setActivityName(activityDB.getActivityName());
        entity.setActivityTime(new ActivityTime(activityDB.getStartTime(), activityDB.getEndTime()));
        entity.setActivityDescription(activityDB.getActivityDescription());
        entity.setCreateTime(activityDB.getCreateTime());
        entity.setUpdateTime(activityDB.getUpdateTime());

        return entity;
    }
}
