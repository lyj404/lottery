package com.lyj.lotterapp.assembler;

import com.lyj.lotterclient.dto.PrizeAddCmd;
import com.lyj.lotterclient.dto.PrizeUpdateCmd;
import com.lyj.lotterclient.dto.data.PrizeVO;
import com.lyj.lotterdomain.prize.Inventory;
import com.lyj.lotterdomain.prize.PrizeEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author lyj
 * @date 2023-07-04
 **/
public class PrizeAssembler {
    private PrizeAssembler(){}

    public static PrizeEntity toAddEntity(PrizeAddCmd cmd) {
        PrizeEntity entity = new PrizeEntity();
        entity.setPrizeName(cmd.getPrizeName());
        entity.setInventory(new Inventory(cmd.getInventory()));
        entity.setMoney(new BigDecimal(cmd.getMoney().toString()));
        entity.setType(cmd.getType());
        entity.setCreateTime(LocalDateTime.now());
        entity.setUpdateTime(LocalDateTime.now());
        return entity;
    }

    public static PrizeVO toPrizeVO(PrizeEntity prize) {
        PrizeVO prizeVO = new PrizeVO();
        prizeVO.setId(prize.getId());
        prizeVO.setPrizeName(prize.getPrizeName());
        prizeVO.setInventory(prize.getInventory().getInventory());
        prizeVO.setMoney(new BigDecimal(prize.getMoney().toString()));
        prizeVO.setType(prize.getType());
        prizeVO.setCreateTime(prize.getCreateTime());
        prizeVO.setUpdateTime(prize.getUpdateTime());
        return prizeVO;
    }

    public static PrizeEntity toUpdateEntity(PrizeUpdateCmd cmd) {
        PrizeEntity entity = new PrizeEntity();
        entity.setId(cmd.getId());
        entity.setPrizeName(cmd.getPrizeName());
        entity.setInventory(new Inventory(cmd.getInventory()));
        entity.setMoney(new BigDecimal(cmd.getMoney().toString()));
        entity.setType(cmd.getType());
        entity.setUpdateTime(LocalDateTime.now());
        return entity;
    }
}
