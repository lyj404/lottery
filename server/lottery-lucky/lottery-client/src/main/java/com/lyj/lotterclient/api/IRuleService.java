package com.lyj.lotterclient.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyj.lotterclient.dto.RuleAddCmd;
import com.lyj.lotterclient.dto.RuleUpdateCmd;
import com.lyj.lotterclient.dto.data.RuleVO;
import com.lyj.lotterclient.dto.query.RuleListByParamQuery;

/**
 * @author lyj
 * @date 2023-07-05
 **/
public interface IRuleService {

    /**
     * 添加规则
     *
     * @param cmd RuleAddCmd
     * @return RuleVO
     */
    RuleVO add(RuleAddCmd cmd);

    /**
     * 修改规则
     *
     * @param cmd RuleUpdateCmd
     * @return RuleVO
     */
    RuleVO update(RuleUpdateCmd cmd);

    /**
     * 分页查询规则
     *
     * @param query RuleListByParamQuery
     * @return IPage<RuleVO>
     */
    IPage<RuleVO> page(RuleListByParamQuery query);

    /**
     * 查询单个规则
     *
     * @param id Long
     * @return RuleVO
     */
    RuleVO one(Long id);
}