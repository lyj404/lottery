package com.lyj.lotterinfrastructure.gateway.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyj.config.enums.ExceptionEnum;
import com.lyj.config.utils.AssertUtil;
import com.lyj.lotterclient.dto.query.PrizeListByParamQuery;
import com.lyj.lotterdomain.gateway.PrizeGateway;
import com.lyj.lotterdomain.prize.PrizeEntity;
import com.lyj.lotterinfrastructure.convertor.PrizeConverter;
import com.lyj.lotterinfrastructure.gateway.impl.dataobject.PrizeDB;
import com.lyj.lotterinfrastructure.gateway.impl.mapper.PrizeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author lyj
 * @date 2023-07-04
 **/
@Component
@RequiredArgsConstructor
public class PrizeGatewayImpl implements PrizeGateway {
    private final PrizeMapper prizeMapper;

    @Override
    public PrizeEntity save(PrizeEntity entity) {
        if (Objects.isNull(entity.getId())) {
            return addPrize(entity);
        }
        return updatePrize(entity);
    }

    private PrizeEntity updatePrize(PrizeEntity entity) {
        PrizeDB prizeDB = PrizeConverter.toPrizeDB(entity);
        AssertUtil.isTrue(prizeMapper.updateById(prizeDB) <= 0, ExceptionEnum.UPDATE_ERROR.getDescription());
        return PrizeConverter.toEntity(prizeDB);
    }

    private PrizeEntity addPrize(PrizeEntity entity) {
        PrizeDB prizeDB = PrizeConverter.toPrizeDB(entity);
        AssertUtil.isTrue(prizeMapper.insert(prizeDB) <= 0, ExceptionEnum.ADD_ERROR.getDescription());
        return PrizeConverter.toEntity(prizeDB);
    }

    @Override
    public IPage<PrizeEntity> listByParamQuery(PrizeListByParamQuery query) {
        IPage<PrizeDB> page = prizeMapper.page(new Page<>(query.getPageIndex(), query.getPageSize()), query);
        return page.convert(PrizeConverter::toEntity);
    }

    @Override
    public int reductInventory(Long prizeId, Integer number) {
        return prizeMapper.reductInventory(prizeId, number);
    }
}
