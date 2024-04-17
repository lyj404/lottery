package com.lyj.lotterclient.dto;

import com.alibaba.cola.dto.Command;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author lyj
 * @date 2023-07-13
 **/
@Data
public class AcceptPrizeAddCmd extends Command {
    /**
     * 记录id
     */
    @NotNull(message = "记录id不为空")
    private Long recordId;
    /**
     * 电话
     */
    @NotNull(message = "电话不为空")
    private String phone;

    /**
     * 地址
     */
    @NotNull(message = "不为空")
    private String address;
}
