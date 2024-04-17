package com.lyj.lotterinfrastructure.convertor;

import com.lyj.lotterdomain.award.AwardEntity;
import com.lyj.lotterdomain.award.AwardNumber;
import com.lyj.lotterinfrastructure.gateway.impl.dataobject.AwardDB;

/**
 * @author lyj
 * @date 2023-07-06
 **/
public class AwardConverter {
    private AwardConverter(){}
    public static AwardDB toAwardDB(AwardEntity entity) {
        AwardDB awardDB = new AwardDB();
        awardDB.setId(entity.getId());
        awardDB.setPrizeId(entity.getPrizeId());
        awardDB.setActivityId(entity.getActivityId());
        awardDB.setNumber(entity.getNumber().getNumber());
        awardDB.setAwardName(entity.getAwardName());
        awardDB.setProbability(entity.getProbability());
        awardDB.setCreateTime(entity.getCreateTime());
        awardDB.setUpdateTime(entity.getUpdateTime());

        return awardDB;
    }

    public static AwardEntity toEntity(AwardDB awardDB) {
        AwardEntity entity = new AwardEntity();
        entity.setId(awardDB.getId());
        entity.setPrizeId(awardDB.getPrizeId());
        entity.setPrizeName(awardDB.getPrizeName());
        entity.setActivityId(awardDB.getActivityId());
        entity.setNumber(new AwardNumber(awardDB.getNumber()));
        entity.setAwardName(awardDB.getAwardName());
        entity.setProbability(awardDB.getProbability());
        entity.setCreateTime(awardDB.getCreateTime());
        entity.setUpdateTime(awardDB.getUpdateTime());

        return entity;
    }
}
