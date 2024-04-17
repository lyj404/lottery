package com.lyj.lotterclient.dto.query;

import com.alibaba.cola.dto.PageQuery;
import lombok.Data;

/**
 * @author lyj
 * @date 2023-07-06
 **/
@Data
public class AwardListByParamQuery extends PageQuery {
    private Long id;

    private Long activityId;

    private String activityName;

    private String awardName;
}
