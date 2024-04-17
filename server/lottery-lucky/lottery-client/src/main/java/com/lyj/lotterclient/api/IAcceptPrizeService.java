package com.lyj.lotterclient.api;

import com.lyj.lotterclient.dto.AcceptPrizeAddCmd;
import com.lyj.lotterclient.dto.data.AcceptPrizeVO;

/**
 * @author lyj
 * @date 2023-07-13
 **/
public interface IAcceptPrizeService {

    AcceptPrizeVO one(Long recordId);

    AcceptPrizeVO add(AcceptPrizeAddCmd cmd);
}
