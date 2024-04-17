package com.lyj.lotterapp.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyj.lotterapp.prize.command.PrizeAddCmdExe;
import com.lyj.lotterapp.prize.command.PrizeUpdateCmdExe;
import com.lyj.lotterapp.prize.query.PrizeListByParamQueryExe;
import com.lyj.lotterclient.api.IPrizeService;
import com.lyj.lotterclient.dto.PrizeAddCmd;
import com.lyj.lotterclient.dto.PrizeUpdateCmd;
import com.lyj.lotterclient.dto.data.PrizeVO;
import com.lyj.lotterclient.dto.query.PrizeListByParamQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * @author lyj
 * @date 2023-07-04
 **/
@Service
@RequiredArgsConstructor
public class PrizeServiceImpl implements IPrizeService {
    private final PrizeAddCmdExe prizeAddCmdExe;
    private final PrizeUpdateCmdExe prizeUpdateCmdExe;
    private final PrizeListByParamQueryExe prizeListByParamQueryExe;

    @Override
    public PrizeVO add(PrizeAddCmd cmd) {
        return prizeAddCmdExe.execute(cmd);
    }

    @Override
    public PrizeVO update(PrizeUpdateCmd cmd) {
        return prizeUpdateCmdExe.execute(cmd);
    }

    @Override
    public IPage<PrizeVO> page(PrizeListByParamQuery query) {
        return prizeListByParamQueryExe.execute(query);
    }

    @Override
    public PrizeVO one(Long id) {
        PrizeListByParamQuery query = new PrizeListByParamQuery();
        query.setId(id);
        IPage<PrizeVO> page = page(query);
        if (CollectionUtils.isEmpty(page.getRecords())) {
            return null;
        }
        return page.getRecords().get(0);
    }
}
