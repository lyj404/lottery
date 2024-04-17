package com.lyj.lotteradapter.web.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyj.common.annotation.ResponseResult;
import com.lyj.lotterclient.api.IAwardService;
import com.lyj.lotterclient.dto.AwardAddCmd;
import com.lyj.lotterclient.dto.AwardUpdateCmd;
import com.lyj.lotterclient.dto.data.AwardVO;
import com.lyj.lotterclient.dto.query.AwardListByParamQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author lyj
 * @date 2023-07-06
 **/
@ResponseResult
@RequiredArgsConstructor
@RequestMapping("/v1/admin/award")
public class AdminAwardController {
    private final IAwardService awardService;

    /**
     * 添加奖项
     *
     * @param cmd AwardAddCmd
     * @return AwardVO
     */
    @PostMapping("/add")
    public AwardVO add(@Validated @RequestBody AwardAddCmd cmd) {
        return awardService.add(cmd);
    }

    /**
     * 修改奖项
     *
     * @param cmd AwardUpdateCmd
     * @return AwardVO
     */
    @PostMapping("/update")
    public AwardVO update(@Validated @RequestBody AwardUpdateCmd cmd) {
        return awardService.update(cmd);
    }

    /**
     * 查询单个奖项
     *
     * @param id Long
     * @return AwardVO
     */
    @GetMapping("/one/{id}")
    public AwardVO one(@PathVariable Long id) {
        return awardService.one(id);
    }

    /**
     * 分页查询奖项
     *
     * @param query AwardListByParamQuery
     * @return IPage<AwardVO>
     */
    @GetMapping("/page")
    public IPage<AwardVO> page(@RequestBody AwardListByParamQuery query) {
        return awardService.page(query);
    }
}
