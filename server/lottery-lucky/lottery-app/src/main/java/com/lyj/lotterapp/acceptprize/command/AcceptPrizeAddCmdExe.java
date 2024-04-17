package com.lyj.lotterapp.acceptprize.command;

import com.lyj.lotterapp.assembler.AcceptPrizeAssembler;
import com.lyj.lotterclient.dto.AcceptPrizeAddCmd;
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
public class AcceptPrizeAddCmdExe {
    private final AcceptPrizeGateway acceptPrizeGateway;

    public AcceptPrizeVO execute(AcceptPrizeAddCmd cmd) {
        AcceptPrizeEntity acceptPrizeEntity = acceptPrizeGateway.save(AcceptPrizeAssembler.toEntity(cmd));
        return AcceptPrizeAssembler.toAcceptPrizeVO(acceptPrizeEntity);
    }
}
