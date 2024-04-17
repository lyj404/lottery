package com.lyj.lotterapp.records.command;

import com.lyj.lotterclient.dto.RecordsUpdateStatusCmd;
import com.lyj.lotterdomain.gateway.RecordsGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author lyj
 * @date 2023-07-12
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class RecordsUpdateStatusCmdExe {
    private final RecordsGateway recordsGateway;

    public Boolean execute(RecordsUpdateStatusCmd cmd) {
        Boolean result = recordsGateway.update(cmd.getId(), cmd.getStatus());
        log.info("修改记录，记录id：{}，状态：{}", cmd.getId(), cmd.getStatus());
        return result;
    }
}