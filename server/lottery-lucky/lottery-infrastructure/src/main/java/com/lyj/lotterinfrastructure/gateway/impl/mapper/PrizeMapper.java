package com.lyj.lotterinfrastructure.gateway.impl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyj.lotterclient.dto.query.PrizeListByParamQuery;
import com.lyj.lotterinfrastructure.gateway.impl.dataobject.PrizeDB;
import org.apache.ibatis.annotations.Param;

/**
 * @author lyj
 * @description 针对表【prize(奖品表)】的数据库操作Mapper
 * @createDate 2023-06-30 11:08:53
 * @Entity com.lyj.lotteryuser.po.Prize
 */
public interface PrizeMapper extends BaseMapper<PrizeDB> {

    IPage<PrizeDB> page(@Param("prizeDBPage") Page<PrizeDB> prizeDBPage, @Param("query") PrizeListByParamQuery query);

    int reductInventory(@Param("prizeId") Long prizeId, @Param("number") Integer number);
}




