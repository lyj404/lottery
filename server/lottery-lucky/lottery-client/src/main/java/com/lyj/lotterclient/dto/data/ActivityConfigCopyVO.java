package com.lyj.lotterclient.dto.data;

import com.lyj.lotterclient.dto.ActivityAddCmd;
import com.lyj.lotterclient.dto.AwardAddCmd;
import lombok.Data;

import java.util.List;

/**
 * @author lyj
 * @date 2023-07-08
 **/
@Data
public class ActivityConfigCopyVO {
    private ActivityAddCmd activityAddCmd;

    private List<Long> ruleIdList;

    private List<AwardAddCmd> awardAddCmdList;
}
