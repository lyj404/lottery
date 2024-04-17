package com.lyj.lotterclient.dto.query;

import com.alibaba.cola.dto.PageQuery;
import lombok.Data;

import java.util.List;

/**
 * @author lyj
 * @date 2023-07-05
 **/
@Data
public class RuleListByParamQuery extends PageQuery {
    /**
     * 主键id
     */
    private Long id;

    private List<Long> ids;

    /**
     * 规则名称
     */
    private String ruleName;
}
