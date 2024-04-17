package com.lyj.lotterapp.service;

import com.lyj.lotterapp.acceptprize.command.AcceptPrizeAddCmdExe;
import com.lyj.lotterapp.acceptprize.query.AcceptPrizeOneQueryExe;
import com.lyj.lotterapp.records.command.RecordsUpdateStatusCmdExe;
import com.lyj.lotterclient.api.IAcceptPrizeService;
import com.lyj.lotterclient.dto.AcceptPrizeAddCmd;
import com.lyj.lotterclient.dto.RecordsUpdateStatusCmd;
import com.lyj.lotterclient.dto.data.AcceptPrizeVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lyj
 * @date 2023-07-13
 **/
@Service
@RequiredArgsConstructor
public class AcceptPrizeServiceImpl implements IAcceptPrizeService {
    private final AcceptPrizeAddCmdExe acceptPrizeAddCmdExe;
    private final RecordsUpdateStatusCmdExe recordsUpdateStatusCmdExe;
    private final AcceptPrizeOneQueryExe acceptPrizeOneQueryExe;

    @Override
    public AcceptPrizeVO one(Long recordId) {
        return acceptPrizeOneQueryExe.execute(recordId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AcceptPrizeVO add(AcceptPrizeAddCmd cmd) {
        AcceptPrizeVO acceptPrizeVO = acceptPrizeAddCmdExe.execute(cmd);

        final var statusCmd = new RecordsUpdateStatusCmd();
        statusCmd.setId(cmd.getRecordId());
        statusCmd.setStatus(2);
        recordsUpdateStatusCmdExe.execute(statusCmd);

        return acceptPrizeVO;
    }
}
