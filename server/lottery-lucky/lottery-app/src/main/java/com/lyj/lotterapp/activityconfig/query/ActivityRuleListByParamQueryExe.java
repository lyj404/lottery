package com.lyj.lotterapp.activityconfig.query;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyj.lotterapp.assembler.ActivityRuleAssembler;
import com.lyj.lotterclient.dto.data.ActivityRuleVO;
import com.lyj.lotterclient.dto.query.ActivityRuleListByParamQuery;
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
public class ActivityRuleListByParamQueryExe {
    private final ActivityRuleGateway activityRuleGateway;

    public List<ActivityRuleVO> execute(ActivityRuleListByParamQuery query) {
        List<ActivityRuleEntity> list = activityRuleGateway.list(query);
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        }
        return new Page<ActivityRuleEntity>()
                .setRecords(list)
                .convert(ActivityRuleAssembler::toActivityRuleVO)
                .getRecords();
    }
}