package com.lyj.lotterinfrastructure.gateway.impl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyj.lotterclient.dto.query.AwardListByParamQuery;
import com.lyj.lotterinfrastructure.gateway.impl.dataobject.AwardDB;
import org.apache.ibatis.annotations.Param;

/**
 * @author lyj
 * @description 针对表【award(奖项表)】的数据库操作Mapper
 * @createDate 2023-06-30 11:08:53
 * @Entity com.lyj.lotteryuser.po.Award
 */
public interface AwardMapper extends BaseMapper<AwardDB> {

    IPage<AwardDB> page(@Param("awardDBPage") Page<AwardDB> awardDBPage, @Param("query") AwardListByParamQuery query);

    int reduceAwardNumber(@Param("awardId") Long awardId, @Param("num") int num);
}




