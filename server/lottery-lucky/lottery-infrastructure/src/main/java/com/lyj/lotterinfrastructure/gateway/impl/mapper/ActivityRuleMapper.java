package com.lyj.lotterinfrastructure.gateway.impl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyj.lotterclient.dto.query.ActivityRuleListByParamQuery;
import com.lyj.lotterinfrastructure.gateway.impl.dataobject.ActivityRuleDB;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lyj
 * @description 针对表【activity_rule(活动规则关联表)】的数据库操作Mapper
 * @createDate 2023-07-07 14:47:42
 * @Entity com.lyj.lotteryuser.po.ActivityRule
 */
public interface ActivityRuleMapper extends BaseMapper<ActivityRuleDB> {

    List<ActivityRuleDB> list(@Param("query") ActivityRuleListByParamQuery query);

    void deleteByActivityId(@Param("activityId") Long activityId);
}




