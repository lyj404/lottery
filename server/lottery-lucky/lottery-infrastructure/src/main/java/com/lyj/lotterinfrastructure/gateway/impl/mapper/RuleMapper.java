package com.lyj.lotterinfrastructure.gateway.impl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyj.lotterclient.dto.query.RuleListByParamQuery;
import com.lyj.lotterinfrastructure.gateway.impl.dataobject.RuleDB;
import org.apache.ibatis.annotations.Param;

/**
 * @author lyj
 * @description 针对表【rule(规则表)】的数据库操作Mapper
 * @createDate 2023-06-30 11:08:54
 * @Entity com.lyj.lotteryuser.po.Rule
 */
public interface RuleMapper extends BaseMapper<RuleDB> {

    IPage<RuleDB> page(@Param("ruleDBPage") Page<RuleDB> ruleDBPage, @Param("query") RuleListByParamQuery query);
}




