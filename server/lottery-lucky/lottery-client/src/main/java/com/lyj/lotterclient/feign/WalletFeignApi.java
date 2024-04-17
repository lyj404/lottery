package com.lyj.lotterclient.feign;

import com.lyj.lotterclient.feign.form.WalletUpdateForm;
import com.lyj.lotterclient.feign.vo.WalletUpdateResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author lyj
 * @date 2023-07-14
 **/
@FeignClient(name = "lottery-wallet", path = "/v1/feign/wallet")
public interface WalletFeignApi {

    /**
     * 修改余额并初始化钱包
     *
     * @param form WalletUpdateForm
     * @return WalletUpdateResultVO
     */
    @PostMapping("/update")
    WalletUpdateResultVO updateBalance(@RequestBody WalletUpdateForm form);

    /**
     * 初始化用户钱包
     *
     * @param userId 用户id
     */
    @PostMapping("/init")
    void initUserWallet(@RequestParam("userId") Long userId);
}
