package com.lyj.lotterapp.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyj.config.utils.SecurityUtil;
import com.lyj.lotterapp.activity.command.ActivityAddCmdExe;
import com.lyj.lotterapp.activity.command.ActivityUpdateCmdExe;
import com.lyj.lotterapp.activity.command.RedisReduceAwardNumberLotteryExe;
import com.lyj.lotterapp.activity.query.ActivityListByParamQueryExe;
import com.lyj.lotterapp.context.ActivityLotteryContext;
import com.lyj.lotterclient.api.IActivityConfigService;
import com.lyj.lotterclient.api.IActivityService;
import com.lyj.lotterclient.dto.ActivityAddCmd;
import com.lyj.lotterclient.dto.ActivityUpdateCmd;
import com.lyj.lotterclient.dto.data.ActivityVO;
import com.lyj.lotterclient.dto.data.LotteryResultVO;
import com.lyj.lotterclient.dto.query.ActivityListByParamQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * @author lyj
 * @date 2023-07-07
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements IActivityService {
    private final ActivityAddCmdExe activityAddCmdExe;
    private final ActivityUpdateCmdExe activityUpdateCmdExe;
    private final ActivityListByParamQueryExe activityListByParamQueryExe;
    private final RedisReduceAwardNumberLotteryExe defaultLotteryExe;
    private final IActivityConfigService activityConfigService;

    @Override
    public ActivityVO add(ActivityAddCmd cmd) {
        return activityAddCmdExe.execute(cmd);
    }

    @Override
    public ActivityVO update(ActivityUpdateCmd cmd) {
        return activityUpdateCmdExe.execute(cmd);
    }

    @Override
    public ActivityVO one(Long id) {
        final var query = new ActivityListByParamQuery();
        query.setId(id);
        IPage<ActivityVO> page = page(query);
        if (CollectionUtils.isEmpty(page.getRecords())) {
            return null;
        }
        return page.getRecords().get(0);
    }

    @Override
    public LotteryResultVO lottery(Long activityId) {
        log.info("用户：{}, 开始抽奖", SecurityUtil.getUserName());
        ActivityLotteryContext context = new ActivityLotteryContext()
                .setActivityConfigVO(activityConfigService.one(activityId));
        return defaultLotteryExe.execute(context);
    }

    @Override
    public IPage<ActivityVO> page(ActivityListByParamQuery query) {
        return activityListByParamQueryExe.execute(query);
    }
}
