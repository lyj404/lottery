package com.lyj.lotterapp.assembler;

import com.lyj.lotterclient.dto.AcceptPrizeAddCmd;
import com.lyj.lotterclient.dto.data.AcceptPrizeVO;
import com.lyj.lotterdomain.acceptprize.AcceptPrizeEntity;

import java.time.LocalDateTime;

/**
 * @author lyj
 * @date 2023-07-13
 **/
public class AcceptPrizeAssembler {
    private AcceptPrizeAssembler(){}
    public static AcceptPrizeEntity toEntity(AcceptPrizeAddCmd cmd) {
        AcceptPrizeEntity acceptPrizeEntity = new AcceptPrizeEntity();
        acceptPrizeEntity.setRecordId(cmd.getRecordId());
        acceptPrizeEntity.setPhone(cmd.getPhone());
        acceptPrizeEntity.setAddress(cmd.getAddress());
        acceptPrizeEntity.setCreateTime(LocalDateTime.now());
        acceptPrizeEntity.setUpdateTime(LocalDateTime.now());

        return acceptPrizeEntity;
    }

    public static AcceptPrizeVO toAcceptPrizeVO(AcceptPrizeEntity entity) {
        AcceptPrizeVO acceptPrizeVO = new AcceptPrizeVO();
        acceptPrizeVO.setId(entity.getId());
        acceptPrizeVO.setPhone(entity.getPhone());
        acceptPrizeVO.setAddress(entity.getAddress());
        acceptPrizeVO.setCreateTime(LocalDateTime.now());

        return acceptPrizeVO;
    }
}
