package com.lyj.lotterinfrastructure.gateway.impl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyj.lotterclient.dto.query.RecordsListByParamQuery;
import com.lyj.lotterinfrastructure.gateway.impl.dataobject.RecordsDB;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @author lyj
 * @description 针对表【records(抽奖记录表)】的数据库操作Mapper
 * @createDate 2023-06-30 11:08:54
 * @Entity com.lyj.lotteryuser.po.Records
 */
public interface RecordsMapper extends BaseMapper<RecordsDB> {

    IPage<RecordsDB> page(@Param("recordsDBPage") Page<RecordsDB> recordsDBPage, @Param("query") RecordsListByParamQuery query);

    Integer updateStatus(@Param("id") Long id, @Param("status") Integer status);

    BigDecimal getPrizeMoneyByRecordId(@Param("recordId") Long recordId);
}




