package com.lyj.lotterapp.activity.command;

import com.lyj.lotterapp.assembler.ActivityAssembler;
import com.lyj.lotterclient.dto.ActivityAddCmd;
import com.lyj.lotterclient.dto.data.ActivityVO;
import com.lyj.lotterdomain.activity.ActivityEntity;
import com.lyj.lotterdomain.gateway.ActivityGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author lyj
 * @date 2023-07-07
 **/
@Component
@RequiredArgsConstructor
public class ActivityAddCmdExe {
    private final ActivityGateway activityGateway;

    public ActivityVO execute(ActivityAddCmd cmd) {
        ActivityEntity entity = activityGateway.save(ActivityAssembler.toAddEntity(cmd));
        return ActivityAssembler.toActivityVO(entity);
    }
}
