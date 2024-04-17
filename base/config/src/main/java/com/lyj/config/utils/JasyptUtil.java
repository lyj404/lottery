package com.lyj.config.utils;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 敏感信息加密工具类
 *
 * @author lyj
 * @date 2023-06-29
 **/
public class JasyptUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JasyptUtil.class);

    private JasyptUtil() {
    }

    /**
     * 加密解密使用的盐
     */
    protected static final String PASSWORD = "123456";
    protected static final String PREFIX = "ENC(";
    protected static final String SUFFIX = ")";

    private static PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();

    static {
        encryptor.setConfig(getConfig());
    }


    public static void main(String[] args) {
        String encrypt = encrypt("1111");
        LOGGER.info("加密后：{}", PREFIX + encrypt + SUFFIX);
        String decrypt = decrypt(encrypt);
        LOGGER.info("解密后：{}" ,decrypt);
    }

    /**
     * 加密
     * @param encrypt 密码
     * @return String
     */
    public static String encrypt(String encrypt){
        return encryptor.encrypt(encrypt);
    }

    /**
     * 解密
     * @param decrypt 需要解密的密码
     * @return String
     */
    public static String decrypt(String decrypt){
        return encryptor.decrypt(decrypt);
    }

    /**
     * 敏感信息加密配置
     * @return SimpleStringPBEConfig
     */
    public static SimpleStringPBEConfig getConfig(){
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        //加密解密使用的盐
        config.setPassword(PASSWORD);
        //加密算法
        config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");
        return config;
    }
}
