package com.lyj.lotterclient.dto.query;

import com.alibaba.cola.dto.PageQuery;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author lyj
 * @date 2023-07-12
 **/
@Data
@RequiredArgsConstructor
public class RecordsListByParamQuery extends PageQuery {
    private Long id;
    private Long activityId;

    private Long userId;

    /**
     * 是否中将，true为中将，false为未中奖
     */
    private Boolean winTheLottery;

    private Integer status;
}
