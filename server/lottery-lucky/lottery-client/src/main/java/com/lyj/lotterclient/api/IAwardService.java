package com.lyj.lotterclient.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyj.lotterclient.dto.AwardAddCmd;
import com.lyj.lotterclient.dto.AwardUpdateCmd;
import com.lyj.lotterclient.dto.data.AwardVO;
import com.lyj.lotterclient.dto.query.AwardListByParamQuery;

/**
 * @author lyj
 * @date 2023-07-05
 **/
public interface IAwardService {
    AwardVO add(AwardAddCmd cmd);

    AwardVO update(AwardUpdateCmd cmd);

    IPage<AwardVO> page(AwardListByParamQuery query);

    AwardVO one(Long id);
}
