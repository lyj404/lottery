package com.lyj.lotterclient.dto.query;

import com.alibaba.cola.dto.PageQuery;
import lombok.Data;

/**
 * @author lyj
 * @date 2023-07-04
 **/
@Data
public class PrizeListByParamQuery extends PageQuery {
    private Long id;

    private String prizeName;

    private Integer type;
}
