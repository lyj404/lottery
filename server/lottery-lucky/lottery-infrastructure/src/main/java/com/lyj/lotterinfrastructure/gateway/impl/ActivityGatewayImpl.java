package com.lyj.lotterinfrastructure.gateway.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyj.config.enums.ExceptionEnum;
import com.lyj.config.utils.AssertUtil;
import com.lyj.lotterclient.dto.query.ActivityListByParamQuery;
import com.lyj.lotterdomain.activity.ActivityEntity;
import com.lyj.lotterdomain.gateway.ActivityGateway;
import com.lyj.lotterinfrastructure.convertor.ActivityConverter;
import com.lyj.lotterinfrastructure.gateway.impl.dataobject.ActivityDB;
import com.lyj.lotterinfrastructure.gateway.impl.mapper.ActivityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author lyj
 * @date 2023-07-07
 **/
@Component
@RequiredArgsConstructor
public class ActivityGatewayImpl implements ActivityGateway {
    private final ActivityMapper activityMapper;


    @Override
    public ActivityEntity save(ActivityEntity entity) {
        if (Objects.isNull(entity.getId())) {
            return addActivity(entity);
        }
        return upadteActivity(entity);
    }

    private ActivityEntity upadteActivity(ActivityEntity entity) {
        ActivityDB activityDB = ActivityConverter.toActivityDB(entity);
        AssertUtil.isTrue(activityMapper.updateById(activityDB) <= 0, ExceptionEnum.UPDATE_ERROR.getDescription());
        return ActivityConverter.toActivityEntity(activityDB);
    }

    private ActivityEntity addActivity(ActivityEntity entity) {
        ActivityDB activityDB = ActivityConverter.toActivityDB(entity);
        AssertUtil.isTrue(activityMapper.insert(activityDB) <= 0, ExceptionEnum.ADD_ERROR.getDescription());
        return ActivityConverter.toActivityEntity(activityDB);
    }

    @Override
    public IPage<ActivityEntity> page(ActivityListByParamQuery query) {
        IPage<ActivityDB> page = activityMapper.page(new Page<>(query.getPageIndex(), query.getPageSize()), query);
        return page.convert(ActivityConverter::toActivityEntity);
    }
}
