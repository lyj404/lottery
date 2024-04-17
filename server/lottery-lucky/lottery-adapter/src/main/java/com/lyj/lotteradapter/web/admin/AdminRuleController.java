package com.lyj.lotteradapter.web.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyj.common.annotation.ResponseResult;
import com.lyj.lotterclient.api.IRuleService;
import com.lyj.lotterclient.dto.RuleAddCmd;
import com.lyj.lotterclient.dto.RuleUpdateCmd;
import com.lyj.lotterclient.dto.data.RuleVO;
import com.lyj.lotterclient.dto.query.RuleListByParamQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author lyj
 * @date 2023-07-05
 **/
@ResponseResult
@RequestMapping("/v1/admin/rule")
@RequiredArgsConstructor
public class AdminRuleController {
    private final IRuleService ruleService;

    /**
     * 添加规则
     *
     * @param cmd RuleAddCmd
     * @return RuleVO
     */
    @PostMapping("/add")
    public RuleVO add(@Validated @RequestBody RuleAddCmd cmd) {
        return ruleService.add(cmd);
    }

    /**
     * 修改规则
     *
     * @param cmd RuleUpdateCmd
     * @return RuleVO
     */
    @PostMapping("/update")
    public RuleVO update(@Validated @RequestBody RuleUpdateCmd cmd) {
        return ruleService.update(cmd);
    }

    /**
     * 查询单个规则
     *
     * @param id Long
     * @return RuleVO
     */
    @GetMapping("/one/{id}")
    public RuleVO one(@PathVariable Long id) {
        return ruleService.one(id);
    }

    /**
     * 分页查询规则
     *
     * @param query RuleListByParamQuery
     * @return IPage<RuleVO>
     */
    @GetMapping("/page")
    public IPage<RuleVO> page(@RequestBody RuleListByParamQuery query) {
        return ruleService.page(query);
    }
}
