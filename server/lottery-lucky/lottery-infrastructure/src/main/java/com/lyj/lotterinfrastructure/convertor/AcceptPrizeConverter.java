package com.lyj.lotterinfrastructure.convertor;

import com.lyj.lotterdomain.acceptprize.AcceptPrizeEntity;
import com.lyj.lotterinfrastructure.gateway.impl.dataobject.AcceptPrizeDB;

import java.time.LocalDateTime;

/**
 * @author lyj
 * @date 2023-07-13
 **/
public class AcceptPrizeConverter {
    private AcceptPrizeConverter(){}
    public static AcceptPrizeDB toAcceptPrizeDB(AcceptPrizeEntity entity) {
        AcceptPrizeDB acceptPrizeDB = new AcceptPrizeDB();
        acceptPrizeDB.setRecordId(entity.getRecordId());
        acceptPrizeDB.setPhone(entity.getPhone());
        acceptPrizeDB.setAddress(entity.getAddress());
        acceptPrizeDB.setCreateTime(LocalDateTime.now());
        acceptPrizeDB.setUpdateTime(LocalDateTime.now());

        return acceptPrizeDB;
    }

    public static AcceptPrizeEntity toAcceptPrizeEntity(AcceptPrizeDB acceptPrizeDB) {
        AcceptPrizeEntity acceptPrizeEntity = new AcceptPrizeEntity();
        acceptPrizeEntity.setId(acceptPrizeDB.getId());
        acceptPrizeEntity.setRecordId(acceptPrizeDB.getRecordId());
        acceptPrizeEntity.setPhone(acceptPrizeDB.getPhone());
        acceptPrizeEntity.setAddress(acceptPrizeDB.getAddress());
        acceptPrizeEntity.setCreateTime(acceptPrizeDB.getCreateTime());
        acceptPrizeEntity.setUpdateTime(acceptPrizeDB.getUpdateTime());

        return acceptPrizeEntity;
    }
}
