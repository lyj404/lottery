package com.lyj.lotterinfrastructure.gateway.impl;

import com.lyj.config.enums.ExceptionEnum;
import com.lyj.config.utils.AssertUtil;
import com.lyj.lotterdomain.acceptprize.AcceptPrizeEntity;
import com.lyj.lotterdomain.gateway.AcceptPrizeGateway;
import com.lyj.lotterinfrastructure.convertor.AcceptPrizeConverter;
import com.lyj.lotterinfrastructure.gateway.impl.dataobject.AcceptPrizeDB;
import com.lyj.lotterinfrastructure.gateway.impl.mapper.AcceptPrizeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author lyj
 * @date 2023-07-13
 **/
@Component
@RequiredArgsConstructor
public class AcceptPrizeGatewayImpl implements AcceptPrizeGateway {
    private final AcceptPrizeMapper acceptPrizeMapper;

    @Override
    public AcceptPrizeEntity save(AcceptPrizeEntity entity) {
        AcceptPrizeDB acceptPrizeDB = AcceptPrizeConverter.toAcceptPrizeDB(entity);
        AssertUtil.isTrue(acceptPrizeMapper.insert(acceptPrizeDB) <= 0,
                ExceptionEnum.ADD_ERROR.getDescription());
        return AcceptPrizeConverter.toAcceptPrizeEntity(acceptPrizeDB);
    }

    @Override
    public AcceptPrizeEntity one(Long recordId) {
        AcceptPrizeDB acceptPrizeDB = acceptPrizeMapper.getByRecordId(recordId);
        return AcceptPrizeConverter.toAcceptPrizeEntity(acceptPrizeDB);
    }
}
