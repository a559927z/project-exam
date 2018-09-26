package com.ks.constants;

import com.ks.dto.PublicUserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * Title: ${type_name} <br/>
 * <p>
 * Description: <br/>
 *
 * @author jxzhang
 * @DATE 2018年09月20日 17:54
 * @Verdion 1.0 版本
 * ${tags}
 */
public class EisWebContext {

    private EisWebContext() {
    }

    public static String getCurrentUserId() {
        return "superAdmin";
    }

    /**
     * Get current logged in user
     *
     * @return 永不为null
     * @throws RuntimeException
     */
    public static PublicUserInfo getCurrentUser() {
        Subject subject = SecurityUtils.getSubject();
        Object principal = subject.getPrincipal();
        if (principal instanceof PublicUserInfo) {
            return (PublicUserInfo) principal;
        }
        throw new RuntimeException("There is no user available, maybe the session has expired");
    }

    /**
     * 判断用户是否登录
     */
    public static boolean isLogin() {
        return SecurityUtils.getSubject().isAuthenticated();
    }
}
