package com.lyj.lotterapp.assembler;

import com.lyj.lotterclient.dto.AwardAddCmd;
import com.lyj.lotterclient.dto.AwardUpdateCmd;
import com.lyj.lotterclient.dto.data.AwardVO;
import com.lyj.lotterdomain.award.AwardEntity;
import com.lyj.lotterdomain.award.AwardNumber;

import java.time.LocalDateTime;

/**
 * @author lyj
 * @date 2023-07-06
 **/
public class AwardAssembler {
    private AwardAssembler(){}
    public static AwardEntity toAddEntity(AwardAddCmd cmd) {
        AwardEntity entity = new AwardEntity();
        entity.setPrizeId(cmd.getPrizeId());
        entity.setActivityId(cmd.getActivityId());
        entity.setNumber(new AwardNumber(cmd.getNumber()));
        entity.setAwardName(cmd.getAwardName());
        entity.setProbability(cmd.getProbability());
        entity.setCreateTime(LocalDateTime.now());
        entity.setUpdateTime(LocalDateTime.now());

        return entity;
    }

    public static AwardVO toAwardVo(AwardEntity entity) {
        AwardVO awardVO = new AwardVO();
        awardVO.setId(entity.getId());
        awardVO.setPrizeId(entity.getPrizeId());
        awardVO.setPrizeName(entity.getPrizeName());
        awardVO.setActivityId(entity.getActivityId());
        awardVO.setNumber(entity.getNumber().getNumber());
        awardVO.setAwardName(entity.getAwardName());
        awardVO.setProbability(entity.getProbability());
        awardVO.setCreateTime(entity.getCreateTime());
        awardVO.setUpdateTime(entity.getUpdateTime());

        return awardVO;
    }

    public static AwardEntity toUpdateEntity(AwardUpdateCmd cmd) {
        AwardEntity entity = new AwardEntity();
        entity.setId(cmd.getId());
        entity.setPrizeId(cmd.getPrizeId());
        entity.setActivityId(cmd.getActivityId());
        entity.setNumber(new AwardNumber(cmd.getNumber()));
        entity.setAwardName(cmd.getAwardName());
        entity.setProbability(cmd.getProbability());
        entity.setUpdateTime(LocalDateTime.now());

        return entity;
    }

    public static AwardAddCmd toAwardAddCmd(AwardVO awardVO) {
        AwardAddCmd awardAddCmd = new AwardAddCmd();
        awardAddCmd.setPrizeId(awardVO.getPrizeId());
        awardAddCmd.setNumber(awardAddCmd.getNumber());
        awardAddCmd.setAwardName(awardAddCmd.getAwardName());
        awardAddCmd.setProbability(awardAddCmd.getProbability());

        return awardAddCmd;
    }

    public static AwardEntity toAwardEntity(AwardVO awardVO) {
        AwardEntity entity = new AwardEntity();
        entity.setId(awardVO.getId());
        entity.setPrizeId(awardVO.getPrizeId());
        entity.setActivityId(awardVO.getActivityId());
        entity.setNumber(new AwardNumber(awardVO.getNumber()));
        entity.setAwardName(awardVO.getAwardName());
        entity.setProbability(awardVO.getProbability());
        entity.setCreateTime(awardVO.getCreateTime());
        entity.setUpdateTime(awardVO.getUpdateTime());
        return entity;
    }
}
