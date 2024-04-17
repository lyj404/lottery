package com.lyj.lotterinfrastructure.gateway.impl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyj.lotterinfrastructure.gateway.impl.dataobject.AcceptPrizeDB;
import org.apache.ibatis.annotations.Param;

/**
 * @author lyj
 * @description 针对表【accept_prize(领奖表)】的数据库操作Mapper
 * @createDate 2023-06-30 11:08:53
 * @Entity com.lyj.lotteryuser.po.AcceptPrize
 */
public interface AcceptPrizeMapper extends BaseMapper<AcceptPrizeDB> {

    AcceptPrizeDB getByRecordId(@Param("recordId") Long recordId);
}




