package com.lyj.lotterclient.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyj.lotterclient.dto.ActivityAddCmd;
import com.lyj.lotterclient.dto.ActivityUpdateCmd;
import com.lyj.lotterclient.dto.data.ActivityVO;
import com.lyj.lotterclient.dto.data.LotteryResultVO;
import com.lyj.lotterclient.dto.query.ActivityListByParamQuery;

/**
 * @author lyj
 * @date 2023-07-07
 **/
public interface IActivityService {

    ActivityVO add(ActivityAddCmd cmd);

    ActivityVO update(ActivityUpdateCmd cmd);

    ActivityVO one(Long id);

    IPage<ActivityVO> page(ActivityListByParamQuery query);

    /**
     * 用户抽奖接口
     *
     * @param activityId Long
     * @return LotteryResultVO
     */
    LotteryResultVO lottery(Long activityId);
}
