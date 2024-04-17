package com.lyj.config.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import com.lyj.config.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.CharEncoding;

import java.io.FileInputStream;

/**
 * @author lyj
 * @date 2023-07-10
 **/
@Slf4j
public class FileLoadUtil {
    private FileLoadUtil() {
    }

    public static String read(String fileName) {
        String val = "";
        try {
            val = IoUtil.read(new FileInputStream(FileUtil.file(fileName)), CharEncoding.UTF_8);
        } catch (Exception e) {
            log.error("文件路径读取失败：{}", fileName, e);
            throw new BaseException(String.format("文件路径读取失败: %s", fileName));
        }
        return val;
    }
}
