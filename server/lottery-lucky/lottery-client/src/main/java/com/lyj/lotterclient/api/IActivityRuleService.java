package com.lyj.lotterclient.api;

import com.lyj.lotterclient.dto.ActivityRuleAddCmd;
import com.lyj.lotterclient.dto.data.ActivityRuleVO;
import com.lyj.lotterclient.dto.query.ActivityRuleListByParamQuery;

import java.util.List;

/**
 * @author lyj
 * @date 2023-07-07
 **/
public interface IActivityRuleService {

    ActivityRuleVO add(ActivityRuleAddCmd cmd);

    List<ActivityRuleVO> list(ActivityRuleListByParamQuery query);
}
