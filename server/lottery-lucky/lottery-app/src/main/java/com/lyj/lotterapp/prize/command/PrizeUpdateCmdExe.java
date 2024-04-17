package com.lyj.lotterapp.prize.command;

import com.lyj.lotterapp.assembler.PrizeAssembler;
import com.lyj.lotterclient.dto.PrizeUpdateCmd;
import com.lyj.lotterclient.dto.data.PrizeVO;
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
public class PrizeUpdateCmdExe {
    private final PrizeGateway prizeGateway;

    public PrizeVO execute(PrizeUpdateCmd cmd) {
        PrizeEntity prize = prizeGateway.save(PrizeAssembler.toUpdateEntity(cmd));
        return PrizeAssembler.toPrizeVO(prize);
    }
}
