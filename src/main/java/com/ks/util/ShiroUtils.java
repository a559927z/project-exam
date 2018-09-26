package com.ks.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * http://jinnianshilongnian.iteye.com/blog/2021439
 * Title: ${type_name} <br/>
 * <p>
 * Description: <br/>
 *
 * @author jxzhang
 * @DATE 2018年09月26日 10:56
 * @Verdion 1.0 版本
 * ${tags}
 */
public class ShiroUtils {

    /**
     * 散列算法
     *
     * @param password 密码
     * @param salt     盐
     * @return
     */
    public static String shiroMd5Hash(String password, String salt) {

        return shiroMd5Hash(password, salt, null).toString();

    }

    /**
     * 散列算法
     *
     * @param password       密码
     * @param salt           盐
     * @param hashIterations 还可以指定散列次数
     * @return
     */
    public static String shiroMd5Hash(String password, String salt, Integer hashIterations) {
        if (null == hashIterations) {
            return new Md5Hash(password, salt).toString();

        }
        return new Md5Hash(password, salt, hashIterations).toString();
    }

    public static void main(String[] args) {
        String str = "sa123456";
        String salt = "superAdminkingsing";
        String s = ShiroUtils.shiroMd5Hash(str, salt, 2);
        System.out.println(s);
    }
}
