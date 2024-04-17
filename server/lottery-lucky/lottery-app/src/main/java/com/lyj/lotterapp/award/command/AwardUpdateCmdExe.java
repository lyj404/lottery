package com.lyj.lotterapp.award.command;

import com.lyj.lotterapp.assembler.AwardAssembler;
import com.lyj.lotterclient.dto.AwardUpdateCmd;
import com.lyj.lotterclient.dto.data.AwardVO;
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
public class AwardUpdateCmdExe {
    private final AwardGateway awardGateway;

    public AwardVO execute(AwardUpdateCmd cmd) {
        AwardEntity entity = awardGateway.save(AwardAssembler.toUpdateEntity(cmd));
        return AwardAssembler.toAwardVo(entity);
    }
}
