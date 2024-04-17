package com.lyj.lotterdomain.gateway;

import com.lyj.lotterdomain.acceptprize.AcceptPrizeEntity;

/**
 * @author lyj
 * @date 2023-07-13
 **/
public interface AcceptPrizeGateway {
    AcceptPrizeEntity save(AcceptPrizeEntity toEntity);

    AcceptPrizeEntity one(Long recordId);
}
