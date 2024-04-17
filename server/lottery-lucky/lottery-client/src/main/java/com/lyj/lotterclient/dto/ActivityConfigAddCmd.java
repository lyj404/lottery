package com.lyj.lotterclient.dto;

import lombok.Data;

import java.util.List;

/**
 * @author lyj
 * @date 2023-07-07
 **/
@Data
public class ActivityConfigAddCmd {
    private ActivityAddCmd activityAddCmd;

    private List<Long> ruleIdList;

    private List<AwardAddCmd> awardAddCmdList;

}
