package com.lyj.lotteradapter.web;

import com.lyj.common.annotation.ResponseResult;
import com.lyj.lotterclient.api.IAcceptPrizeService;
import com.lyj.lotterclient.dto.AcceptPrizeAddCmd;
import com.lyj.lotterclient.dto.data.AcceptPrizeVO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author lyj
 * @date 2023-07-13
 **/
@ResponseResult
@RequestMapping("/v1/accept")
@RequiredArgsConstructor
public class AcceptPrizeController {
    private final IAcceptPrizeService acceptPrizeService;

    /**
     * 获取领奖信息
     *
     * @param recordId Long
     * @return AcceptPrizeVO
     */
    @GetMapping("/one")
    public AcceptPrizeVO one(@RequestParam("recordId") Long recordId) {
        return acceptPrizeService.one(recordId);
    }

    /**
     * 领取奖品
     *
     * @param cmd AcceptPrizeAddCmd
     * @return AcceptPrizeVO
     */
    @PostMapping("/add")
    public AcceptPrizeVO add(@Validated @RequestBody AcceptPrizeAddCmd cmd) {
        return acceptPrizeService.add(cmd);
    }
}
