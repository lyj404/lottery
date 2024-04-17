package com.lyj.lotteradapter.web.admin;

import com.lyj.common.annotation.ResponseResult;
import com.lyj.lotterclient.api.IActivityConfigService;
import com.lyj.lotterclient.dto.ActivityConfigAddCmd;
import com.lyj.lotterclient.dto.data.ActivityConfigCopyVO;
import com.lyj.lotterclient.dto.data.ActivityConfigVO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author lyj
 * @date 2023-07-07
 **/
@ResponseResult
@RequiredArgsConstructor
@RequestMapping("/v1/admin/activityConfig")
public class AdminActivityConfigController {
    private final IActivityConfigService activityConfigService;

    /**
     * 添加活动配置
     * @param cmd ActivityConfigAddCmd
     * @return ActivityConfigVO
     */
    @PostMapping("/add")
    public ActivityConfigVO add(@Validated @RequestBody ActivityConfigAddCmd cmd){
        return activityConfigService.add(cmd);
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
     * 复制
     *
     * @param id Long
     * @return ActivityConfigVO
     */
    @PostMapping("/copy")
    public ActivityConfigCopyVO copy(@RequestParam("id") Long id) {
        return activityConfigService.copy(id);
    }
}
