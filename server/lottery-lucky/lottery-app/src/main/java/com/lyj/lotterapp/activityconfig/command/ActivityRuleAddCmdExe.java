package com.lyj.lotterapp.activityconfig.command;

import com.lyj.config.exception.BaseException;
import com.lyj.lotterapp.assembler.ActivityRuleAssembler;
import com.lyj.lotterclient.dto.ActivityRuleAddCmd;
import com.lyj.lotterclient.dto.data.ActivityRuleVO;
import com.lyj.lotterdomain.activityrule.ActivityRuleEntity;
import com.lyj.lotterdomain.gateway.ActivityRuleGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lyj
 * @date 2023-07-07
 **/
@Component
@RequiredArgsConstructor
public class ActivityRuleAddCmdExe {
    private final ActivityRuleGateway activityRuleGateway;

    public ActivityRuleVO execute(ActivityRuleAddCmd cmd) {
        ActivityRuleEntity ruleEntity = activityRuleGateway.save(ActivityRuleAssembler.toAddEntity(cmd));
        return ActivityRuleAssembler.toActivityRuleVO(ruleEntity);
    }

    public List<ActivityRuleVO> execute(List<ActivityRuleAddCmd> cmds) {
        if (CollectionUtils.isEmpty(cmds)) {
            throw new BaseException("数据有误");
        }
        List<ActivityRuleVO> result = new ArrayList<>();
        for (var cmd : cmds) {
            result.add(execute(cmd));
        }
        return result;
    }
}
