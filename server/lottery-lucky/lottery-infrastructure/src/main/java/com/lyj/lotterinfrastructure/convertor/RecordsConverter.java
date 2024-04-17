package com.lyj.lotterinfrastructure.convertor;

import com.lyj.lotterdomain.records.RecordStatus;
import com.lyj.lotterdomain.records.RecordsEntity;
import com.lyj.lotterinfrastructure.gateway.impl.dataobject.RecordsDB;

/**
 * @author lyj
 * @date 2023-07-11
 **/
public class RecordsConverter {
    private RecordsConverter(){}

    public static RecordsDB toRecordDB(RecordsEntity entity) {
        RecordsDB recordsDB = new RecordsDB();
        recordsDB.setId(entity.getId());
        recordsDB.setUserId(entity.getUserId());
        recordsDB.setActivityId(entity.getActivityId());
        recordsDB.setActivityName(entity.getActivityName());
        recordsDB.setAwardId(entity.getAwardId());
        recordsDB.setIsWinning(entity.getIsWinning());
        recordsDB.setStatus(entity.getStatus().getState());
        recordsDB.setCreateTime(entity.getCreateTime());
        recordsDB.setUpdateTime(entity.getUpdateTime());

        return recordsDB;
    }

    public static RecordsEntity toEntity(RecordsDB recordsDB) {
        RecordsEntity entity = new RecordsEntity();
        entity.setId(recordsDB.getId());
        entity.setUserId(recordsDB.getUserId());
        entity.setActivityId(recordsDB.getActivityId());
        entity.setActivityName(recordsDB.getActivityName());
        entity.setAwardId(recordsDB.getAwardId());
        entity.setAwardName(recordsDB.getAwardName());
        entity.setPrizeName(recordsDB.getPrizeName());
        entity.setPrizeType(recordsDB.getPrizeType());
        entity.setIsWinning(recordsDB.getIsWinning());
        entity.setStatus(new RecordStatus(recordsDB.getStatus()));
        entity.setCreateTime(recordsDB.getCreateTime());
        entity.setUpdateTime(recordsDB.getUpdateTime());

        return entity;
    }
}
