package com.lyj.lotterapp.assembler;

import com.lyj.lotterclient.dto.RecordsAddCmd;
import com.lyj.lotterclient.dto.data.RecordsVO;
import com.lyj.lotterdomain.records.RecordStatus;
import com.lyj.lotterdomain.records.RecordsEntity;

import java.time.LocalDateTime;

/**
 * @author lyj
 * @date 2023-07-12
 **/
public class RecordsAssembler {
    private RecordsAssembler(){}

    public static RecordsEntity toAddEntity(RecordsAddCmd cmd) {
        RecordsEntity entity = new RecordsEntity();
        entity.setId(cmd.getId());
        entity.setUserId(cmd.getUserId());
        entity.setActivityId(cmd.getActivityId());
        entity.setActivityName(cmd.getActivityName());
        entity.setAwardId(cmd.getAwardId());
        entity.setIsWinning(cmd.getIsWinning());
        entity.setStatus(new RecordStatus(cmd.getStatus()));
        entity.setCreateTime(LocalDateTime.now());
        entity.setUpdateTime(LocalDateTime.now());

        return entity;
    }

    public static RecordsVO toRecordsVO(RecordsEntity entity) {
        RecordsVO recordsVO = new RecordsVO();
        recordsVO.setId(entity.getId());
        recordsVO.setUserId(entity.getUserId());
        recordsVO.setActivityId(entity.getActivityId());
        recordsVO.setActivityName(entity.getActivityName());
        recordsVO.setAwardId(entity.getAwardId());
        recordsVO.setAwardName(entity.getAwardName());
        recordsVO.setPrizeName(entity.getPrizeName());
        recordsVO.setPrizeType(entity.getPrizeType());
        recordsVO.setIsWinning(entity.getIsWinning());
        recordsVO.setStatus(entity.getStatus().getState());
        recordsVO.setCreateTime(LocalDateTime.now());
        recordsVO.setUpdateTime(LocalDateTime.now());

        return recordsVO;
    }
}
