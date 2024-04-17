package com.lyj.lotterapp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyj.config.utils.AssertUtil;
import com.lyj.lotterapp.activity.command.ActivityAddCmdExe;
import com.lyj.lotterapp.activity.command.ActivityUpdateCmdExe;
import com.lyj.lotterapp.activity.query.ActivityListByParamQueryExe;
import com.lyj.lotterapp.activityconfig.command.ActivityRuleAddCmdExe;
import com.lyj.lotterapp.activityconfig.command.ActivityRuleDeleteCmdExe;
import com.lyj.lotterapp.activityconfig.query.ActivityRuleListByParamQueryExe;
import com.lyj.lotterapp.assembler.ActivityAssembler;
import com.lyj.lotterapp.assembler.AwardAssembler;
import com.lyj.lotterapp.award.command.AwardAddCmdExe;
import com.lyj.lotterapp.award.command.AwardUpdateCmdExe;
import com.lyj.lotterapp.award.query.AwardListByParamQueryExe;
import com.lyj.lotterapp.listener.event.ActivityCreateEvent;
import com.lyj.lotterapp.rule.query.RuleListByParamQueryExe;
import com.lyj.lotterclient.api.IActivityConfigService;
import com.lyj.lotterclient.dto.*;
import com.lyj.lotterclient.dto.data.*;
import com.lyj.lotterclient.dto.query.ActivityListByParamQuery;
import com.lyj.lotterclient.dto.query.ActivityRuleListByParamQuery;
import com.lyj.lotterclient.dto.query.AwardListByParamQuery;
import com.lyj.lotterclient.dto.query.RuleListByParamQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author lyj
 * @date 2023-07-07
 **/
@Service
@RequiredArgsConstructor
public class ActivityConfigServiceImpl implements IActivityConfigService {
    private final ActivityAddCmdExe activityAddCmdExe;
    private final ActivityRuleAddCmdExe activityRuleAddCmdExe;
    private final AwardAddCmdExe awardAddCmdExe;

    private final ActivityUpdateCmdExe activityUpdateCmdExe;
    private final ActivityRuleDeleteCmdExe activityRuleDeleteCmdExe;
    private final AwardUpdateCmdExe awardUpdateCmdExe;

    private final ActivityListByParamQueryExe activityListByParamQueryExe;
    private final RuleListByParamQueryExe ruleListByParamQueryExe;
    private final ActivityRuleListByParamQueryExe activityRuleListByParamQueryExe;
    private final AwardListByParamQueryExe awardListByParamQueryExe;

    private final ApplicationEventMulticaster applicationEventMulticaster;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ActivityConfigVO add(ActivityConfigAddCmd cmd) {
        //添加活动
        ActivityVO activityVO = activityAddCmdExe.execute(cmd.getActivityAddCmd());
        //添加活动规则
        List<RuleVO> ruleVOList = addActivityRule(activityVO, cmd.getRuleIdList());
        //添加活动的奖品
        List<AwardVO> awardVOList = addAward(activityVO, cmd.getAwardAddCmdList());

        ActivityConfigVO configVO = new ActivityConfigVO();
        configVO.setActivityVO(activityVO);
        configVO.setRuleVOList(ruleVOList);
        configVO.setAwardVOList(awardVOList);

        //发送活动创建事件
        applicationEventMulticaster.multicastEvent(new ActivityCreateEvent("", configVO));

        return configVO;
    }

    @Override
    public ActivityConfigVO one(Long id) {
        //查询活动记录
        final var activityListByParamQuery = new ActivityListByParamQuery();
        activityListByParamQuery.setId(id);
        List<ActivityVO> records = activityListByParamQueryExe.execute(activityListByParamQuery).getRecords();
        AssertUtil.isTrue(CollectionUtils.isEmpty(records), "数据不存在");
        ActivityVO activityVO = records.get(0);
        //查询活动规则
        final var activityRuleListByParamQuery = new ActivityRuleListByParamQuery();
        activityRuleListByParamQuery.setActivityId(activityVO.getId());
        List<ActivityRuleVO> activityRuleVOS = activityRuleListByParamQueryExe.execute(activityRuleListByParamQuery);
        List<RuleVO> ruleVOList = getRuleVOList(activityRuleVOS.stream().map(ActivityRuleVO::getRuleId).toList());
        //查询活动的奖品
        AwardListByParamQuery awardListByParamQuery = new AwardListByParamQuery();
        awardListByParamQuery.setActivityId(activityVO.getId());
        awardListByParamQuery.setPageSize(1000);
        List<AwardVO> awardVOS = awardListByParamQueryExe.execute(awardListByParamQuery).getRecords();

        ActivityConfigVO configVO = new ActivityConfigVO();
        configVO.setActivityVO(activityVO);
        configVO.setRuleVOList(ruleVOList);
        configVO.setAwardVOList(awardVOS);

        return configVO;
    }

    @Override
    public ActivityConfigCopyVO copy(Long id) {
        ActivityConfigCopyVO activityConfigCopyVO = new ActivityConfigCopyVO();

        ActivityConfigVO activityConfigVO = one(id);

        activityConfigCopyVO.setActivityAddCmd(ActivityAssembler.toActivityAddCmd(activityConfigVO.getActivityVO()));
        activityConfigCopyVO.setRuleIdList(activityConfigVO.getRuleVOList().
                stream().map(RuleVO::getId).toList());
        activityConfigCopyVO.setAwardAddCmdList(new Page<AwardVO>().
                setRecords(activityConfigVO.getAwardVOList()).convert(AwardAssembler::toAwardAddCmd).getRecords());

        return activityConfigCopyVO;
    }

    private List<AwardVO> addAward(ActivityVO activityVO, List<AwardAddCmd> awardAddCmdList) {
        AssertUtil.isTrue(CollectionUtils.isEmpty(awardAddCmdList), "奖项不为空");

        List<AwardVO> result = new ArrayList<>();
        for (var awardAddCmd : awardAddCmdList) {
            awardAddCmd.setActivityId(activityVO.getId());
            result.add(awardAddCmdExe.execute(awardAddCmd));
        }
        return result;
    }

    private List<RuleVO> addActivityRule(ActivityVO activityVO, List<Long> ruleIdList) {
        List<ActivityRuleAddCmd> list = new ArrayList<>();
        for (var ruleId : ruleIdList) {
            ActivityRuleAddCmd activityRuleAddCmd = new ActivityRuleAddCmd();
            activityRuleAddCmd.setActivityId(activityVO.getId());
            activityRuleAddCmd.setRuleId(ruleId);
            list.add(activityRuleAddCmd);
        }
        List<ActivityRuleVO> ruleVOList = activityRuleAddCmdExe.execute(list);

        return getRuleVOList(ruleVOList.stream().map(ActivityRuleVO::getRuleId).toList());
    }

    private List<RuleVO> getRuleVOList(List<Long> ruleIdList) {
        RuleListByParamQuery query = new RuleListByParamQuery();
        query.setIds(ruleIdList);
        query.setPageSize(1000);

        return ruleListByParamQueryExe.execute(query).getRecords();
    }
}
