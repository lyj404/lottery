package com.lyj.lotterclient.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyj.lotterclient.dto.PrizeAddCmd;
import com.lyj.lotterclient.dto.PrizeUpdateCmd;
import com.lyj.lotterclient.dto.data.PrizeVO;
import com.lyj.lotterclient.dto.query.PrizeListByParamQuery;

/**
 * @author lyj
 * @date 2023-07-04
 **/
public interface IPrizeService {
    /**
     * 添加奖品
     *
     * @param cmd PrizeAddCmd
     * @return PrizeVO
     */
    PrizeVO add(PrizeAddCmd cmd);

    /**
     * 修改奖品
     *
     * @param cmd PrizeUpdateCmd
     * @return PrizeVO
     */
    PrizeVO update(PrizeUpdateCmd cmd);

    /**
     * 分页查询奖品
     *
     * @param query PrizeListByParamQuery
     * @return IPage<PrizeVO>
     */
    IPage<PrizeVO> page(PrizeListByParamQuery query);

    /**
     * 查询单个奖品
     *
     * @param id Long
     * @return PrizeVO
     */
    PrizeVO one(Long id);
}
