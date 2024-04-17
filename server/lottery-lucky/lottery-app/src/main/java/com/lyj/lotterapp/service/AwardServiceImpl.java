package com.lyj.lotterapp.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyj.lotterapp.award.command.AwardAddCmdExe;
import com.lyj.lotterapp.award.command.AwardUpdateCmdExe;
import com.lyj.lotterapp.award.query.AwardListByParamQueryExe;
import com.lyj.lotterclient.api.IAwardService;
import com.lyj.lotterclient.dto.AwardAddCmd;
import com.lyj.lotterclient.dto.AwardUpdateCmd;
import com.lyj.lotterclient.dto.data.AwardVO;
import com.lyj.lotterclient.dto.query.AwardListByParamQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * @author lyj
 * @date 2023-07-06
 **/
@Service
@RequiredArgsConstructor
public class AwardServiceImpl implements IAwardService {
    private final AwardAddCmdExe awardAddCmdExe;
    private final AwardUpdateCmdExe awardUpdateCmdExe;
    private final AwardListByParamQueryExe awardListByParamQueryExe;

    @Override
    public AwardVO add(AwardAddCmd cmd) {
        return awardAddCmdExe.execute(cmd);
    }

    @Override
    public AwardVO update(AwardUpdateCmd cmd) {
        return awardUpdateCmdExe.execute(cmd);
    }

    @Override
    public IPage<AwardVO> page(AwardListByParamQuery query) {
        return awardListByParamQueryExe.execute(query);
    }

    @Override
    public AwardVO one(Long id) {
        final AwardListByParamQuery query = new AwardListByParamQuery();
        query.setId(id);
        IPage<AwardVO> page = page(query);
        if (CollectionUtils.isEmpty(page.getRecords())) {
            return null;
        }
        return page.getRecords().get(0);
    }
}
