package com.lyj.wallet.api.feign;

import com.lyj.wallet.api.feign.form.WalletUpdateForm;
import com.lyj.wallet.api.feign.vo.WalletUpdateResultVO;
import com.lyj.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author lyj
 * @date 2023-07-14
 **/
@RestController
@RequestMapping("/v1/feign/wallet")
@RequiredArgsConstructor
public class WalletFeign {
    private final WalletService walletService;

    /**
     * 修改余额并初始化钱包
     *
     * @param form WalletUpdateForm
     * @return WalletUpdateResultVO
     */
    @PostMapping("/update")
    public WalletUpdateResultVO updateBalance(@RequestBody WalletUpdateForm form) {
        return walletService.updateBalance(form);
    }

    /**
     * 初始化用户钱包
     *
     * @param userId 用户id
     */
    @PostMapping("/init")
    public void initUserWallet(@RequestParam("userId") Long userId) {
        walletService.initUserWallet(userId);
    }
}
