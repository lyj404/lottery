package com.lyj.lotterapp.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyj.config.utils.AssertUtil;
import com.lyj.config.utils.SecurityUtil;
import com.lyj.lotterapp.records.command.RecordsAddCmdExe;
import com.lyj.lotterapp.records.command.RecordsUpdateStatusCmdExe;
import com.lyj.lotterapp.records.query.RecordMoneyByParamQueryExe;
import com.lyj.lotterapp.records.query.RecordsListByParamQueryExe;
import com.lyj.lotterclient.api.IRecordService;
import com.lyj.lotterclient.dto.RecordsAddCmd;
import com.lyj.lotterclient.dto.RecordsUpdateStatusCmd;
import com.lyj.lotterclient.dto.data.RecordsVO;
import com.lyj.lotterclient.dto.query.RecordsListByParamQuery;
import com.lyj.lotterclient.feign.WalletFeignApi;
import com.lyj.lotterclient.feign.form.WalletUpdateForm;
import com.lyj.lotterclient.feign.vo.WalletUpdateResultVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * @author lyj
 * @date 2023-07-12
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements IRecordService {
    private final RecordsListByParamQueryExe recordsListByParamQueryExe;
    private final RecordMoneyByParamQueryExe recordMoneyByParamQueryExe;
    private final RecordsAddCmdExe recordsAddCmdExe;
    private final RecordsUpdateStatusCmdExe recordsUpdateStatusCmdExe;
    private final WalletFeignApi walletFeignApi;

    @Override
    public IPage<RecordsVO> page(RecordsListByParamQuery query) {
        return recordsListByParamQueryExe.execute(query);
    }

    @Override
    public RecordsVO add(RecordsAddCmd cmd) {
        return recordsAddCmdExe.execute(cmd);
    }

    @Override
    public Boolean update(RecordsUpdateStatusCmd cmd) {
        return recordsUpdateStatusCmdExe.execute(cmd);
    }

    @Override
    public Integer prizeType(Long recordId) {
        return getPrizeByRecordId(recordId).getPrizeType();
    }

    public RecordsVO getPrizeByRecordId(Long recordId) {
        final var query = new RecordsListByParamQuery();
        query.setId(recordId);
        List<RecordsVO> recordsVOList = recordsListByParamQueryExe.execute(query).getRecords();
        AssertUtil.isTrue(CollUtil.isEmpty(recordsVOList) || Objects.isNull(recordsVOList.get(0)),
                "数据不存在");
        return recordsVOList.get(0);
    }

    @Override
    public Boolean exchangeMoney(Long recordId) {
        RecordsVO recordsVO = getPrizeByRecordId(recordId);

        AssertUtil.isTrue(recordsVO.getPrizeType() != 2, "奖品类型兑换出错");
        AssertUtil.isTrue(recordsVO.getStatus() != 1, "记录状态非法");
        //获取奖品金额
        BigDecimal money = recordMoneyByParamQueryExe.execute(recordId);

        //将记录状态改为4
        final var statusCmd = new RecordsUpdateStatusCmd();
        statusCmd.setId(recordId);
        statusCmd.setStatus(4);
        update(statusCmd);

        try {
            //给用户钱包加钱
            final var form = new WalletUpdateForm();
            form.setUpdateMoney(money);
            form.setUserId(SecurityUtil.getUserId());
            WalletUpdateResultVO walletUpdateResultVO = walletFeignApi.updateBalance(form);

            if (Boolean.FALSE.equals(walletUpdateResultVO.getResult())) {
                return Boolean.FALSE;
            }
        } catch (Exception e) {
            log.info("调用修改用户钱包金额失败：", e);
            //回滚记录状态
            statusCmd.setStatus(1);
            update(statusCmd);
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }
}
