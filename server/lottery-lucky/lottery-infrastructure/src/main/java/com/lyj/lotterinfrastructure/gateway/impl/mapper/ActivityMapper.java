package com.lyj.lotterinfrastructure.gateway.impl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyj.lotterclient.dto.query.ActivityListByParamQuery;
import com.lyj.lotterinfrastructure.gateway.impl.dataobject.ActivityDB;
import org.apache.ibatis.annotations.Param;

/**
 * @author lyj
 * @description 针对表【activity(活动表)】的数据库操作Mapper
 * @createDate 2023-06-30 11:08:53
 * @Entity com.lyj.lotteryuser.po.Activity
 */
public interface ActivityMapper extends BaseMapper<ActivityDB> {

    IPage<ActivityDB> page(@Param("activityDBPage") Page<ActivityDB> activityDBPage, @Param("query") ActivityListByParamQuery query);
}




