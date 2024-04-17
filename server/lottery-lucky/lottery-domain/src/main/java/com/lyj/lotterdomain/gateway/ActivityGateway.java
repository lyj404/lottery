package com.lyj.lotterdomain.gateway;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyj.lotterclient.dto.query.ActivityListByParamQuery;
import com.lyj.lotterdomain.activity.ActivityEntity;

/**
 * @author lyj
 * @date 2023-07-07
 **/
public interface ActivityGateway {

    ActivityEntity save(ActivityEntity entity);

    IPage<ActivityEntity> page(ActivityListByParamQuery query);
}
