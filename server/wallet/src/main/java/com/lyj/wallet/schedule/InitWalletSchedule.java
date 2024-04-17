package com.lyj.wallet.schedule;

import com.lyj.common.annotation.DistributedLock;
import com.lyj.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author lyj
 * @date 2023-07-14
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class InitWalletSchedule {
    private final WalletService walletService;

    @Scheduled(cron = "0 0 0/1 * * ?")
    @DistributedLock
    void initWallet() {
        walletService.initAllNotWallet();
    }
}
