package com.lyj.wallet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyj.wallet.api.feign.form.WalletUpdateForm;
import com.lyj.wallet.api.feign.vo.WalletUpdateResultVO;
import com.lyj.wallet.po.Wallet;

/**
 * @author lyj
 * @description 针对表【wallet(钱包表)】的数据库操作Service
 * @createDate 2023-07-14 09:22:34
 */
public interface WalletService extends IService<Wallet> {

    WalletUpdateResultVO updateBalance(WalletUpdateForm form);

    void initUserWallet(Long userId);

    void initAllNotWallet();
}
