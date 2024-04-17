package com.lyj.lotterclient.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyj.lotterclient.dto.RecordsAddCmd;
import com.lyj.lotterclient.dto.RecordsUpdateStatusCmd;
import com.lyj.lotterclient.dto.data.RecordsVO;
import com.lyj.lotterclient.dto.query.RecordsListByParamQuery;

/**
 * @author lyj
 * @date 2023-07-12
 **/
public interface IRecordService {

    IPage<RecordsVO> page(RecordsListByParamQuery query);

    RecordsVO add(RecordsAddCmd cmd);

    Boolean update(RecordsUpdateStatusCmd cmd);

    /**
     * 中将类型
     *
     * @param recordId Long
     * @return 1：商品，2：金钱
     */
    Integer prizeType(Long recordId);

    Boolean exchangeMoney(Long recordId);
}
