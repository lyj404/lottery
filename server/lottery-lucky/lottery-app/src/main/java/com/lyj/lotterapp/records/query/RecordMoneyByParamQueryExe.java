package com.lyj.lotterapp.records.query;

import com.lyj.lotterdomain.gateway.RecordsGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author lyj
 * @date 2023-07-13
 **/
@Component
@RequiredArgsConstructor
public class RecordMoneyByParamQueryExe {
    private final RecordsGateway recordsGateway;

    public BigDecimal execute(Long recordId) {
        return recordsGateway.getPrizeMoneyByRecordId(recordId);
    }
}
