package com.lyj.lotteradapter.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyj.common.annotation.ResponseResult;
import com.lyj.lotterclient.api.IActivityConfigService;
import com.lyj.lotterclient.api.IActivityService;
import com.lyj.lotterclient.dto.data.ActivityConfigVO;
import com.lyj.lotterclient.dto.data.ActivityVO;
import com.lyj.lotterclient.dto.data.LotteryResultVO;
import com.lyj.lotterclient.dto.query.ActivityListByParamQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author lyj
 * @date 2023-07-09
 **/
@ResponseResult
@RequiredArgsConstructor
@RequestMapping("/v1/activity")
public class ActivityController {
    private final IActivityService activityService;
    private final IActivityConfigService activityConfigService;

    /**
     * 查询活动列表
     *
     * @param query ActivityListByParamQuery
     * @return IPage<ActivityVO>
     */
    @GetMapping("/page")
    public IPage<ActivityVO> page(ActivityListByParamQuery query) {
        return activityService.page(query);
    }

    /**
     * 查询单个活动配置
     *
     * @param id Long
     * @return ActivityConfigVO
     */
    @GetMapping("/one")
    public ActivityConfigVO one(@RequestParam("id") Long id) {
        return activityConfigService.one(id);
    }

    /**
     * 抽奖
     *
     * @param id Long
     * @return LotteryResultVO
     */
    @GetMapping("/lottery")
    public LotteryResultVO lottery(@RequestParam("activityId") Long id) {
        return activityService.lottery(id);
    }
}