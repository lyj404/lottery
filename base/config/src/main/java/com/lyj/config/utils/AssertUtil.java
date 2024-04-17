package com.lyj.config.utils;

import com.lyj.config.exception.BaseException;

/**
 * @author lyj
 * @date 2023-07-07
 **/
public class AssertUtil {
    private AssertUtil() {
    }

    /**
     * 条件未true抛出异常
     *
     * @param expression boolean
     * @param message    String
     */
    public static void isTrue(boolean expression, String message) {
        if (expression) {
            throw new BaseException(message);
        }
    }

    /**
     * 条件未false抛出异常
     *
     * @param expression boolean
     * @param message    String
     */
    public static void isFalse(boolean expression, String message) {
        if (!expression) {
            throw new BaseException(message);
        }
    }
}
