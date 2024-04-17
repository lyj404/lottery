package com.lyj.lotterapp.listener.event;

import com.lyj.lotterclient.dto.data.ActivityConfigVO;
import org.springframework.context.ApplicationEvent;

/**
 * @author lyj
 * @date 2023-07-10
 **/
public class ActivityCreateEvent extends ApplicationEvent {
    /**
     * 活动创建id
     */
    private final ActivityConfigVO configVO;

    public ActivityConfigVO getActivityConfig() {
        return configVO;
    }

    public ActivityCreateEvent(Object source, ActivityConfigVO configVO) {
        super(source);
        this.configVO = configVO;
    }
}
