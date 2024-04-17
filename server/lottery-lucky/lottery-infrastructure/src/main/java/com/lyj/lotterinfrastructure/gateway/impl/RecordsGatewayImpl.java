package com.lyj.lotterinfrastructure.gateway.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyj.config.utils.AssertUtil;
import com.lyj.lotterclient.dto.query.RecordsListByParamQuery;
import com.lyj.lotterdomain.gateway.RecordsGateway;
import com.lyj.lotterdomain.records.RecordsEntity;
import com.lyj.lotterinfrastructure.convertor.RecordsConverter;
import com.lyj.lotterinfrastructure.gateway.impl.dataobject.RecordsDB;
import com.lyj.lotterinfrastructure.gateway.impl.mapper.RecordsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author lyj
 * @date 2023-07-12
 **/
@Component
@RequiredArgsConstructor
public class RecordsGatewayImpl implements RecordsGateway {
    private final RecordsMapper recordsMapper;

    @Override
    public RecordsEntity save(RecordsEntity entity) {
        RecordsDB recordsDB = RecordsConverter.toRecordDB(entity);
        AssertUtil.isTrue(recordsMapper.insert(recordsDB) != 1, "添加记录失败");
        return RecordsConverter.toEntity(recordsDB);
    }

    @Override
    public IPage<RecordsEntity> page(RecordsListByParamQuery query) {
        IPage<RecordsDB> page = recordsMapper.page(new Page<>(query.getPageIndex(), query.getPageSize()), query);
        return page.convert(RecordsConverter::toEntity);
    }

    @Override
    public Boolean update(Long id, Integer status) {
        return recordsMapper.updateStatus(id, status) == 1;
    }

    @Override
    public BigDecimal getPrizeMoneyByRecordId(Long recordId) {
        return recordsMapper.getPrizeMoneyByRecordId(recordId);
    }
}