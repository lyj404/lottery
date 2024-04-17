package com.lyj.lotterdomain.gateway;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyj.lotterclient.dto.query.AwardListByParamQuery;
import com.lyj.lotterdomain.award.AwardEntity;

/**
 * @author lyj
 * @date 2023-07-06
 **/
public interface AwardGateway {
    AwardEntity save(AwardEntity entity);

    IPage<AwardEntity> page(AwardListByParamQuery query);

    int reduceAwardNumber(Long awardId, int num);
}
