package com.lyj.wallet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyj.wallet.api.feign.form.WalletUpdateForm;
import com.lyj.wallet.api.feign.vo.WalletUpdateResultVO;
import com.lyj.wallet.mapper.WalletMapper;
import com.lyj.wallet.po.Wallet;
import com.lyj.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @author lyj
 * @description 针对表【wallet(钱包表)】的数据库操作Service实现
 * @createDate 2023-07-14 09:22:34
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class WalletServiceImpl extends ServiceImpl<WalletMapper, Wallet>
        implements WalletService {
    private final WalletMapper walletMapper;

    @Override
    public WalletUpdateResultVO updateBalance(WalletUpdateForm form) {
        Wallet wallet = lambdaQuery()
                .eq(Wallet::getUserId, form.getUserId())
                .one();
        if (Objects.isNull(wallet)) {
            //初始化用户钱包
            try {
                wallet = initWallet(form.getUserId());
            } catch (Exception e) {
                log.error("执行初始化用户钱包失败：", e);
                wallet = lambdaQuery()
                        .eq(Wallet::getUserId, form.getUserId())
                        .one();
            }
        }

        if (Objects.isNull(wallet)) {
            return new WalletUpdateResultVO()
                    .setResult(Boolean.FALSE);
        }

        int i = walletMapper.updateBalance(form.getUserId(), form.getUpdateMoney(), wallet.getBalance());
        if (i != 1) {
            return new WalletUpdateResultVO()
                    .setResult(Boolean.FALSE);
        }
        return new WalletUpdateResultVO()
                .setResult(Boolean.TRUE);
    }

    @Override
    public void initUserWallet(Long userId) {
        initWallet(userId);
    }

    @Override
    public void initAllNotWallet() {
        List<Long> list = walletMapper.notInitWallet();
        list.stream().parallel().forEach(this::initUserWallet);
    }

    private Wallet initWallet(Long userId) {
        Wallet wallet = new Wallet();
        wallet.setUserId(userId);
        wallet.setBalance(new BigDecimal("0"));
        wallet.setCreateTime(LocalDateTime.now());
        wallet.setUpdateTime(LocalDateTime.now());

        save(wallet);

        return wallet;
    }
}




