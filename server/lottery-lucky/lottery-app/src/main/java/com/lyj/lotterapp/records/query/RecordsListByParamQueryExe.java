package com.lyj.lotterapp.records.query;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyj.lotterapp.assembler.RecordsAssembler;
import com.lyj.lotterclient.dto.data.RecordsVO;
import com.lyj.lotterclient.dto.query.RecordsListByParamQuery;
import com.lyj.lotterdomain.gateway.RecordsGateway;
import com.lyj.lotterdomain.records.RecordsEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author lyj
 * @date 2023-07-12
 **/
@Component
@RequiredArgsConstructor
public class RecordsListByParamQueryExe {
    private final RecordsGateway recordsGateway;

    public IPage<RecordsVO> execute(RecordsListByParamQuery query) {
        IPage<RecordsEntity> page = recordsGateway.page(query);
        return page.convert(RecordsAssembler::toRecordsVO);
    }
}
