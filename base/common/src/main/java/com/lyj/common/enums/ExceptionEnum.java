package com.lyj.common.enums;

/**
 * @author lyj
 * @date 2023-06-29
 **/
public enum ExceptionEnum {
    /**
     * 错误前缀
     */
    EXCEPTION_INFO_DESC("Exception_info:{}"),
    EXCEPTION_INFO("Exception_info:");
    private final String description;

    ExceptionEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
