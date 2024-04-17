package com.lyj.lotterapp.service;

import com.lyj.lotterapp.activityconfig.command.ActivityRuleAddCmdExe;
import com.lyj.lotterapp.activityconfig.query.ActivityRuleListByParamQueryExe;
import com.lyj.lotterclient.api.IActivityRuleService;
import com.lyj.lotterclient.dto.ActivityRuleAddCmd;
import com.lyj.lotterclient.dto.data.ActivityRuleVO;
import com.lyj.lotterclient.dto.query.ActivityRuleListByParamQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lyj
 * @date 2023-07-07
 **/
@Service
@RequiredArgsConstructor
public class ActivityRuleServiceImpl implements IActivityRuleService {
    private final ActivityRuleAddCmdExe activityRuleAddCmdExe;
    private final ActivityRuleListByParamQueryExe activityRuleListByParamQueryExe;

    @Override
    public ActivityRuleVO add(ActivityRuleAddCmd cmd) {
        return activityRuleAddCmdExe.execute(cmd);
    }

    @Override
    public List<ActivityRuleVO> list(ActivityRuleListByParamQuery query) {
        return activityRuleListByParamQueryExe.execute(query);
    }
}
