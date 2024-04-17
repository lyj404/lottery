package com.lyj.lotterdomain.user;

import cn.hutool.crypto.digest.MD5;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lyj
 * @date 2023-06-30
 **/
@Getter
@Setter
public class PassWord {
    private EncryptionPassword encryptionPassword;

    public PassWord(EncryptionPassword encryptionPassword) {
        this.encryptionPassword = encryptionPassword;
    }

    public static String getEncryptionPassword(String password) {
        return MD5.create().digestHex(password);
    }

    @Getter
    public static class EncryptionPassword {
        /**
         * 密码
         */
        private String password;

        public EncryptionPassword(String password) {
            this.password = password;
        }
    }

    /**
     * 判断密码相等
     *
     * @param password 密码
     * @return 是否相等
     */
    public Boolean isEqual(String password) {
        return this.encryptionPassword.password.equals(getEncryptionPassword(password));
    }
}
