package com.lyj.lotterinfrastructure.convertor;

import com.lyj.lotterdomain.prize.Inventory;
import com.lyj.lotterdomain.prize.PrizeEntity;
import com.lyj.lotterinfrastructure.gateway.impl.dataobject.PrizeDB;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author lyj
 * @date 2023-07-04
 **/
public class PrizeConverter {
    private PrizeConverter(){}
    public static PrizeDB toPrizeDB(PrizeEntity entity) {
        PrizeDB prizeDB = new PrizeDB();
        prizeDB.setId(entity.getId());
        prizeDB.setPrizeName(entity.getPrizeName());
        prizeDB.setInventory(entity.getInventory().getInventory());
        prizeDB.setType(entity.getType());
        prizeDB.setMoney(new BigDecimal(entity.getMoney().toString()));
        prizeDB.setCreateTime(LocalDateTime.now());
        prizeDB.setUpdateTime(LocalDateTime.now());
        return prizeDB;
    }

    public static PrizeEntity toEntity(PrizeDB prizeDB) {
        PrizeEntity prizeEntity = new PrizeEntity();
        prizeEntity.setId(prizeDB.getId());
        prizeEntity.setPrizeName(prizeDB.getPrizeName());
        prizeEntity.setInventory(new Inventory(prizeDB.getInventory()));
        prizeEntity.setType(prizeDB.getType());
        prizeEntity.setMoney(new BigDecimal(prizeDB.getMoney().toString()));
        prizeEntity.setCreateTime(prizeDB.getCreateTime());
        prizeEntity.setUpdateTime(prizeDB.getUpdateTime());
        return prizeEntity;
    }
}
