package com.lyj.lotterapp.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyj.lotterapp.rule.command.RuleAddCmdExe;
import com.lyj.lotterapp.rule.command.RuleUpdateCmdExe;
import com.lyj.lotterapp.rule.query.RuleListByParamQueryExe;
import com.lyj.lotterclient.api.IRuleService;
import com.lyj.lotterclient.dto.RuleAddCmd;
import com.lyj.lotterclient.dto.RuleUpdateCmd;
import com.lyj.lotterclient.dto.data.RuleVO;
import com.lyj.lotterclient.dto.query.RuleListByParamQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * @author lyj
 * @date 2023-07-05
 **/
@Service
@RequiredArgsConstructor
public class IRuleServiceImpl implements IRuleService {
    private final RuleAddCmdExe ruleAddCmdExe;
    private final RuleUpdateCmdExe ruleUpdateCmdExe;
    private final RuleListByParamQueryExe ruleListByParamQueryExe;

    @Override
    public RuleVO add(RuleAddCmd cmd) {
        return ruleAddCmdExe.execute(cmd);
    }

    @Override
    public RuleVO update(RuleUpdateCmd cmd) {
        return ruleUpdateCmdExe.execute(cmd);
    }

    @Override
    public IPage<RuleVO> page(RuleListByParamQuery query) {
        return ruleListByParamQueryExe.execute(query);
    }

    @Override
    public RuleVO one(Long id) {
        RuleListByParamQuery query = new RuleListByParamQuery();
        query.setId(id);
        IPage<RuleVO> iPage = page(query);
        if (CollectionUtils.isEmpty(iPage.getRecords())) {
            return null;
        }
        return iPage.getRecords().get(0);
    }
}
