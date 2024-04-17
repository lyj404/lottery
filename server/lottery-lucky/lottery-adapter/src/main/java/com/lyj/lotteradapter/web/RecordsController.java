package com.lyj.lotteradapter.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyj.common.annotation.ResponseResult;
import com.lyj.config.utils.SecurityUtil;
import com.lyj.lotterclient.api.IRecordService;
import com.lyj.lotterclient.dto.RecordsUpdateStatusCmd;
import com.lyj.lotterclient.dto.data.RecordsVO;
import com.lyj.lotterclient.dto.query.RecordsListByParamQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author lyj
 * @date 2023-07-13
 **/
@ResponseResult
@RequestMapping("/v1/record")
@RequiredArgsConstructor
public class RecordsController {
    private final IRecordService recordService;

    /**
     * 分页查询中将记录
     *
     * @param query 查询条件
     * @return 分页记录
     */
    @GetMapping("/page")
    public IPage<RecordsVO> page(RecordsListByParamQuery query) {
        query.setUserId(SecurityUtil.getUserId());
        return recordService.page(query);
    }

    /**
     * 用户确认
     *
     * @param cmd RecordsUpdateStatusCmd
     * @return Boolean
     */
    @PostMapping("/update")
    public Boolean update(@RequestBody RecordsUpdateStatusCmd cmd) {
        cmd.setStatus(4);
        return recordService.update(cmd);
    }

    /**
     * 兑换金钱
     *
     * @param recordId Long
     * @return Boolean
     */
    @GetMapping("/exchange")
    public Boolean exchangeMoney(@RequestParam("recordId") Long recordId) {
        return recordService.exchangeMoney(recordId);
    }
}
