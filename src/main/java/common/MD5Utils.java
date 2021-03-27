package common;

import org.springframework.util.DigestUtils;

/**
 * MD5加密工具
 */
public class MD5Utils {
    public static String encrypt(String plainText) {
        return  DigestUtils.md5DigestAsHex(plainText.getBytes());
    }

    public static void main(String[] args) {
        System.out.println(encrypt("123456"));
    }
}
