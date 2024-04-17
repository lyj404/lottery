package com.lyj.lotterdomain.gateway;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyj.config.exception.BaseException;
import com.lyj.lotterclient.dto.query.PrizeListByParamQuery;
import com.lyj.lotterdomain.prize.PrizeEntity;
import org.apache.ibatis.annotations.Param;

/**
 * @author lyj
 * @date 2023-07-02
 **/
public interface PrizeGateway {

    PrizeEntity save(PrizeEntity entity);

    IPage<PrizeEntity> listByParamQuery(@Param("query") PrizeListByParamQuery query);

    default PrizeEntity one(Long id) {
        final var query = new PrizeListByParamQuery();
        query.setId(id);
        PrizeEntity entity = null;
        try {
            entity = listByParamQuery(query).getRecords().get(0);
        } catch (Exception e) {
            throw new BaseException(String.format("奖品id：%s，数据不存在", id));
        }
        return entity;
    }

    int reductInventory(Long prizeId, Integer number);
}
