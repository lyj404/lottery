package com.lyj.lotteradapter.web.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyj.common.annotation.ResponseResult;
import com.lyj.lotterclient.api.IPrizeService;
import com.lyj.lotterclient.dto.PrizeAddCmd;
import com.lyj.lotterclient.dto.PrizeUpdateCmd;
import com.lyj.lotterclient.dto.data.PrizeVO;
import com.lyj.lotterclient.dto.query.PrizeListByParamQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author lyj
 * @date 2023-07-04
 **/
@ResponseResult
@RequiredArgsConstructor
@RequestMapping("/v1/admin/prize")
public class AdminPrizeController {
    private final IPrizeService prizeService;

    /**
     * 添加奖品
     *
     * @param cmd PrizeAddCmd
     * @return PrizeVO
     */
    @PostMapping("/add")
    public PrizeVO add(@Validated @RequestBody PrizeAddCmd cmd) {
        return prizeService.add(cmd);
    }

    /**
     * 修改奖品
     *
     * @param cmd PrizeUpdateCmd
     * @return PrizeVO
     */
    @PostMapping("/update")
    public PrizeVO update(@Validated @RequestBody PrizeUpdateCmd cmd) {
        return prizeService.update(cmd);
    }

    /**
     * 分页获取奖品
     *
     * @param query PrizeUpdateCmd
     * @return IPage<PrizeVO>
     */
    @GetMapping("/page")
    public IPage<PrizeVO> page(@RequestBody PrizeListByParamQuery query) {
        return prizeService.page(query);
    }

    /**
     * 获取单个奖品
     *
     * @param id Long
     * @return PrizeVO
     */
    @GetMapping("/{id}")
    public PrizeVO page(@PathVariable Long id) {
        return prizeService.one(id);
    }
}
