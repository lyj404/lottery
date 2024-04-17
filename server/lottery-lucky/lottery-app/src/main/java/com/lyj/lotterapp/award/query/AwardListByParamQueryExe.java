package com.lyj.lotterapp.award.query;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyj.lotterapp.assembler.AwardAssembler;
import com.lyj.lotterclient.dto.data.AwardVO;
import com.lyj.lotterclient.dto.query.AwardListByParamQuery;
import com.lyj.lotterdomain.award.AwardEntity;
import com.lyj.lotterdomain.gateway.AwardGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author lyj
 * @date 2023-07-06
 **/
@Component
@RequiredArgsConstructor
public class AwardListByParamQueryExe {
    private final AwardGateway awardGateway;

    public IPage<AwardVO> execute(AwardListByParamQuery query) {
        IPage<AwardEntity> page = awardGateway.page(query);
        return page.convert(AwardAssembler::toAwardVo);
    }
}
