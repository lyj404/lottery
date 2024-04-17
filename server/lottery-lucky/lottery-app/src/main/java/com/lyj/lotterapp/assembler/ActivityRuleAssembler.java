package com.lyj.lotterapp.assembler;
import java.time.LocalDateTime;

import com.lyj.lotterclient.dto.ActivityRuleAddCmd;
import com.lyj.lotterclient.dto.data.ActivityRuleVO;
import com.lyj.lotterdomain.activityrule.ActivityRuleEntity;

import java.util.Date;

/**
 * @author lyj
 * @date 2023-07-07
 **/
public class ActivityRuleAssembler {
    private ActivityRuleAssembler(){}

    public static ActivityRuleEntity toAddEntity(ActivityRuleAddCmd cmd) {
        ActivityRuleEntity activityRuleEntity = new ActivityRuleEntity();
        activityRuleEntity.setActivityId(cmd.getActivityId());
        activityRuleEntity.setRuleId(cmd.getRuleId());
        activityRuleEntity.setCreateTime(LocalDateTime.now());
        activityRuleEntity.setUpdateTime(LocalDateTime.now());

        return activityRuleEntity;
    }

    public static ActivityRuleVO toActivityRuleVO(ActivityRuleEntity ruleEntity) {
        ActivityRuleVO activityRuleVO = new ActivityRuleVO();
        activityRuleVO.setId(ruleEntity.getId());
        activityRuleVO.setActivityId(ruleEntity.getActivityId());
        activityRuleVO.setRuleId(ruleEntity.getRuleId());
        activityRuleVO.setCreateTime(new Date());
        activityRuleVO.setUpdateTime(new Date());

        return activityRuleVO;
    }
}
