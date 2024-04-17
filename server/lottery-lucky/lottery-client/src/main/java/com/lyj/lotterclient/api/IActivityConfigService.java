package com.lyj.lotterclient.api;

import com.lyj.lotterclient.dto.ActivityConfigAddCmd;
import com.lyj.lotterclient.dto.ActivityConfigUpdateCmd;
import com.lyj.lotterclient.dto.data.ActivityConfigCopyVO;
import com.lyj.lotterclient.dto.data.ActivityConfigVO;

/**
 * @author lyj
 * @date 2023-07-07
 **/
public interface IActivityConfigService {
    ActivityConfigVO add(ActivityConfigAddCmd cmd);

    ActivityConfigVO one(Long id);

    ActivityConfigCopyVO copy(Long id);
}
