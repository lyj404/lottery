package com.lyj.wallet.api.feign.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author lyj
 * @date 2023-07-14
 **/
@Data
@Accessors(chain = true)
public class WalletUpdateResultVO {
    private Boolean result;
}
