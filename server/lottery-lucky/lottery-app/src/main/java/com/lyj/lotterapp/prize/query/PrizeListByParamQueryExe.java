package com.lyj.lotterapp.prize.query;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyj.lotterapp.assembler.PrizeAssembler;
import com.lyj.lotterclient.dto.data.PrizeVO;
import com.lyj.lotterclient.dto.query.PrizeListByParamQuery;
import com.lyj.lotterdomain.gateway.PrizeGateway;
import com.lyj.lotterdomain.prize.PrizeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author lyj
 * @date 2023-07-04
 **/
@Component
@RequiredArgsConstructor
public class PrizeListByParamQueryExe {
    private final PrizeGateway prizeGateway;

    public IPage<PrizeVO> execute(PrizeListByParamQuery query) {
        IPage<PrizeEntity> page = prizeGateway.listByParamQuery(query);
        return page.convert(PrizeAssembler::toPrizeVO);
    }
}
