package com.lyj.lotterapp.activity.query;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyj.lotterapp.assembler.ActivityAssembler;
import com.lyj.lotterclient.dto.data.ActivityVO;
import com.lyj.lotterclient.dto.query.ActivityListByParamQuery;
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
public class ActivityListByParamQueryExe {
    private final ActivityGateway activityGateway;

    public IPage<ActivityVO> execute(ActivityListByParamQuery query) {
        IPage<ActivityEntity> page = activityGateway.page(query);
        return page.convert(ActivityAssembler::toActivityVO);
    }
}