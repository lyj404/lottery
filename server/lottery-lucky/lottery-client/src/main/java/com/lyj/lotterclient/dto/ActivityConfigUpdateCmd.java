package com.lyj.lotterclient.dto;

import lombok.Data;

import java.util.List;

/**
 * @author lyj
 * @date 2023-07-07
 **/
@Data
public class ActivityConfigUpdateCmd {
    private ActivityUpdateCmd activityUpdateCmd;

    private List<Long> ruleIdList;

    private List<AwardUpdateCmd> awardUpdateCmdList;
}
