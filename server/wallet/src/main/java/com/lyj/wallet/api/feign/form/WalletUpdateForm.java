package com.lyj.wallet.api.feign.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author lyj
 * @date 2023-07-14
 **/
@Data
public class WalletUpdateForm {
    private Long userId;

    private BigDecimal updateMoney;
}
