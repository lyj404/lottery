package com.lyj.lotterapp.records.command;

import com.lyj.lotterapp.assembler.RecordsAssembler;
import com.lyj.lotterclient.dto.RecordsAddCmd;
import com.lyj.lotterclient.dto.data.RecordsVO;
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
public class RecordsAddCmdExe {
    private final RecordsGateway recordsGateway;

    public RecordsVO execute(RecordsAddCmd cmd) {
        RecordsEntity entity = recordsGateway.save(RecordsAssembler.toAddEntity(cmd));
        return RecordsAssembler.toRecordsVO(entity);
    }
}
