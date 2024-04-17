package com.lyj.wallet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyj.wallet.po.Wallet;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author lyj
 * @description 针对表【wallet(钱包表)】的数据库操作Mapper
 * @createDate 2023-07-14 09:22:34
 * @Entity com.lyj.lotteryuser.po.Wallet
 */
public interface WalletMapper extends BaseMapper<Wallet> {

    int updateBalance(@Param("userId") Long userId, @Param("updateMoney") BigDecimal updateMoney, @Param("balance") BigDecimal balance);

    List<Long> notInitWallet();
}




