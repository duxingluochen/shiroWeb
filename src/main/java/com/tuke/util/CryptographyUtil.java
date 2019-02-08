package com.tuke.util;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * 加密工具
 */
public class CryptographyUtil {

    /**
     * 加密
     * @param str
     * @return
     */
    public static String encBase64(String str){
        return Base64.encodeToString(str.getBytes());
    }

    /**
     * 解密
     * @param str
     * @return
     */
    public static String decBase64(String str){
       return Base64.decodeToString(str);
    }

    /**
     * md5加密
     * @param str
     * @param salt
     * @return
     */
    public static String md5(String str, String salt){
        return new Md5Hash(str, salt).toString();
    }

    public static void main(String[] args) {
        String password = "123456";
        System.out.println("加密："+CryptographyUtil.encBase64(password));
        System.out.println("加密："+CryptographyUtil.decBase64("MTIzNDU2"));
        System.out.println("md5加密："+CryptographyUtil.md5(password,"12345"));
    }
}
