package com.lyj.lotterclient.dto.data;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @author lyj
 * @date 2023-07-07
 **/
@Data
public class ActivityConfigVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 2405172041950251807L;

    private ActivityVO activityVO;

    private List<RuleVO> ruleVOList;

    private List<AwardVO> awardVOList;
}