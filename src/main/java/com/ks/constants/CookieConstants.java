package com.ks.constants;

/**
 * Title: ${type_name} <br/>
 * <p>
 * Description: <br/>
 *
 * @author jxzhang
 * @DATE 2018年09月28日 18:20
 * @Verdion 1.0 版本
 * ${tags}
 */
public interface CookieConstants {

    /**
     * 版本
     */
    String VERSION = "20181101";

    /**
     * 三个月
     */
    int MAX_AGE = 2160 * 60 * 60;

    /**
     * 用户信息
     */
    String USER_INFO_KEY = "userInfo";

    //================================== localCatch
    /**
     * 单点登录key
     */
    String USER_LOGIN_KEY = "user_login_key_";

    /**
     * 用户信息
     */
    String USER_INFO_OBJ = "user_info_obj_";
}
