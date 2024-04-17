package com.lyj.lotterclient.dto;

import com.alibaba.cola.dto.Command;
import lombok.Data;

/**
 * @author lyj
 * @date 2023-07-12
 **/
@Data
public class RecordsUpdateStatusCmd extends Command {
    private Long id;

    /**
     * 状态，0，1，2，3
     */
    private Integer status;
}
