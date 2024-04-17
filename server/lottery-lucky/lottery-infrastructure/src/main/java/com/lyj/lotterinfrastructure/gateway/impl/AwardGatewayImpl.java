package com.lyj.lotterinfrastructure.gateway.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyj.config.enums.ExceptionEnum;
import com.lyj.config.utils.AssertUtil;
import com.lyj.lotterclient.dto.query.AwardListByParamQuery;
import com.lyj.lotterdomain.award.AwardEntity;
import com.lyj.lotterdomain.gateway.AwardGateway;
import com.lyj.lotterinfrastructure.convertor.AwardConverter;
import com.lyj.lotterinfrastructure.gateway.impl.dataobject.AwardDB;
import com.lyj.lotterinfrastructure.gateway.impl.mapper.AwardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author lyj
 * @date 2023-07-06
 **/
@Component
@RequiredArgsConstructor
public class AwardGatewayImpl implements AwardGateway {
    private final AwardMapper awardMapper;

    @Override
    public AwardEntity save(AwardEntity entity) {
        if (Objects.isNull(entity.getId())) {
            return addAward(entity);
        }
        return upateAward(entity);
    }

    private AwardEntity upateAward(AwardEntity entity) {
        AwardDB awardDB = AwardConverter.toAwardDB(entity);
        AssertUtil.isTrue(awardMapper.updateById(awardDB) <= 0, ExceptionEnum.UPDATE_ERROR.getDescription());
        return AwardConverter.toEntity(awardDB);
    }

    private AwardEntity addAward(AwardEntity entity) {
        AwardDB awardDB = AwardConverter.toAwardDB(entity);
        AssertUtil.isTrue(awardMapper.insert(awardDB) <= 0, ExceptionEnum.ADD_ERROR.getDescription());
        return AwardConverter.toEntity(awardDB);
    }

    @Override
    public IPage<AwardEntity> page(AwardListByParamQuery query) {
        IPage<AwardDB> page = awardMapper.page(new Page<>(query.getPageIndex(), query.getPageSize()), query);
        return page.convert(AwardConverter::toEntity);
    }

    @Override
    public int reduceAwardNumber(Long awardId, int num) {
        return awardMapper.reduceAwardNumber(awardId, num);
    }
}
