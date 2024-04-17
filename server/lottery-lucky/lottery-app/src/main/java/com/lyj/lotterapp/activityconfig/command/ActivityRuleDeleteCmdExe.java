package com.lyj.lotterapp.activityconfig.command;

import com.lyj.lotterdomain.gateway.ActivityRuleGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author lyj
 * @date 2023-07-07
 **/
@Component
@RequiredArgsConstructor
public class ActivityRuleDeleteCmdExe {
    private final ActivityRuleGateway activityRuleGateway;

    public void execute(Long activityId) {
        activityRuleGateway.deleteByActivityId(activityId);
    }
}
