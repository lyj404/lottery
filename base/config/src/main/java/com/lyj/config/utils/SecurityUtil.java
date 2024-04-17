package com.lyj.config.utils;

import java.util.Map;
import java.util.Objects;

/**
 * @author lyj
 * @date 2023-07-04
 **/
public class SecurityUtil {
    private SecurityUtil() {
    }

    private static final ThreadLocal<Map<String, Object>> userInfo = new ThreadLocal<>();

    public static void addUserInfo(Map<String, Object> user) {
        userInfo.set(user);
    }

    public static void remove() {
        userInfo.remove();
    }

    public static String getUserName() {
        Object name = userInfo.get().get("username");
        return Objects.isNull(name) ? "" : name.toString();
    }

    public static Long getUserId() {
        Object userId = userInfo.get().get("userId");
        return Objects.isNull(userId) ? 0L : Long.valueOf((String) userId);
    }

    public static String get(String key) {
        return String.valueOf(userInfo.get().get(key));
    }
}