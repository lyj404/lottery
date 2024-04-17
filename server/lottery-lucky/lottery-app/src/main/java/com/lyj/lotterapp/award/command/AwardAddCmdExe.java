package com.lyj.lotterapp.award.command;

import com.lyj.config.utils.AssertUtil;
import com.lyj.lotterapp.assembler.AwardAssembler;
import com.lyj.lotterclient.dto.AwardAddCmd;
import com.lyj.lotterclient.dto.data.AwardVO;
import com.lyj.lotterdomain.award.AwardEntity;
import com.lyj.lotterdomain.gateway.AwardGateway;
import com.lyj.lotterdomain.gateway.PrizeGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author lyj
 * @date 2023-07-06
 **/
@Component
@RequiredArgsConstructor
public class AwardAddCmdExe {
    private final AwardGateway awardGateway;
    private final PrizeGateway prizeGateway;

    public AwardVO execute(AwardAddCmd cmd) {
        AssertUtil.isTrue(Objects.isNull(cmd.getActivityId()), "奖项互动id不为空");
        //保存奖项
        AwardEntity entity = awardGateway.save(AwardAssembler.toAddEntity(cmd));
        //扣奖品库存
        //奖品id为零，代表不是一个奖品，是谢谢参与之类，不需要扣减库存
        if (Boolean.FALSE.equals(entity.isPrize())) {
            return AwardAssembler.toAwardVo(entity);
        }
        AssertUtil.isTrue(prizeGateway.reductInventory(cmd.getPrizeId(), cmd.getNumber()) < 1,
                String.format("扣取奖品: %s,出错，库存不足或奖品不存在", cmd.getPrizeId()));
        return AwardAssembler.toAwardVo(entity);
    }
}
