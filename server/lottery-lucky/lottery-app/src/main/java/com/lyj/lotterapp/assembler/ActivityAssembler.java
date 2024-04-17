package com.lyj.lotterapp.assembler;

import com.lyj.lotterclient.dto.ActivityAddCmd;
import com.lyj.lotterclient.dto.ActivityUpdateCmd;
import com.lyj.lotterclient.dto.data.ActivityVO;
import com.lyj.lotterdomain.activity.ActivityEntity;
import com.lyj.lotterdomain.activity.ActivityTime;

import java.time.LocalDateTime;

/**
 * @author lyj
 * @date 2023-07-07
 **/
public class ActivityAssembler {
    private ActivityAssembler(){}
    public static ActivityEntity toAddEntity(ActivityAddCmd cmd) {
        ActivityEntity entity = new ActivityEntity();
        entity.setActivityName(cmd.getActivityName());
        entity.setActivityTime(new ActivityTime(cmd.getStartTime(), cmd.getEndTime()));
        entity.setActivityDescription(cmd.getActivityDescription());
        entity.setCreateTime(LocalDateTime.now());
        entity.setUpdateTime(LocalDateTime.now());

        return entity;
    }

    public static ActivityVO toActivityVO(ActivityEntity entity) {
        ActivityVO activityVO = new ActivityVO();
        activityVO.setId(entity.getId());
        activityVO.setActivityName(entity.getActivityName());
        activityVO.setStartTime(entity.getActivityTime().getStartTime());
        activityVO.setEndTime(entity.getActivityTime().getEndTime());
        activityVO.setStatus(entity.getActivityTime().getStatus().getValue());
        activityVO.setActivityDescription(entity.getActivityDescription());
        activityVO.setCreateTime(entity.getCreateTime());
        activityVO.setUpdateTime(entity.getUpdateTime());

        return activityVO;
    }

    public static ActivityEntity toUpdateEntity(ActivityUpdateCmd cmd) {
        ActivityEntity entity = new ActivityEntity();
        entity.setId(cmd.getId());
        entity.setActivityName(cmd.getActivityName());
        entity.setActivityTime(new ActivityTime(cmd.getStartTime(), cmd.getEndTime()));
        entity.setActivityDescription(cmd.getActivityDescription());
        entity.setUpdateTime(LocalDateTime.now());
        return entity;
    }

    public static ActivityAddCmd toActivityAddCmd(ActivityVO activityVO) {
        ActivityAddCmd activityAddCmd = new ActivityAddCmd();
        activityAddCmd.setActivityName(activityVO.getActivityName());
        activityAddCmd.setStartTime(activityVO.getStartTime());
        activityAddCmd.setEndTime(activityVO.getEndTime());
        activityAddCmd.setActivityDescription(activityAddCmd.getActivityDescription());

        return activityAddCmd;
    }
}
