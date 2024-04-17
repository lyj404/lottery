package com.lyj.lotterdomain.gateway;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyj.lotterclient.dto.query.RecordsListByParamQuery;
import com.lyj.lotterdomain.records.RecordsEntity;

import java.math.BigDecimal;

/**
 * @author lyj
 * @date 2023-07-12
 **/
public interface RecordsGateway {
    RecordsEntity save(RecordsEntity entity);

    IPage<RecordsEntity> page(RecordsListByParamQuery query);

    Boolean update(Long id, Integer status);

    BigDecimal getPrizeMoneyByRecordId(Long recordId);
}
