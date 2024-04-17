package com.lyj.lotterapp.acceptprize.query;

import com.lyj.lotterapp.assembler.AcceptPrizeAssembler;
import com.lyj.lotterclient.dto.data.AcceptPrizeVO;
import com.lyj.lotterdomain.acceptprize.AcceptPrizeEntity;
import com.lyj.lotterdomain.gateway.AcceptPrizeGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author lyj
 * @date 2023-07-13
 **/
@Component
@RequiredArgsConstructor
public class AcceptPrizeOneQueryExe {
    private final AcceptPrizeGateway acceptPrizeGateway;

    public AcceptPrizeVO execute(Long recordId) {
        AcceptPrizeEntity entity = acceptPrizeGateway.one(recordId);
        return AcceptPrizeAssembler.toAcceptPrizeVO(entity);
    }
}