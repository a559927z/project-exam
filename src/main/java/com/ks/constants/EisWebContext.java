package com.ks.constants;

import com.alibaba.fastjson.JSON;
import com.ks.dto.ExamUserInfo;
import com.ks.dto.PublicUserInfo;
import com.ks.utils.CookieUtils;
import com.ks.utils.cache.LoadingCacheUtil;
import com.ks.vo.VisitorVo;
import net.chinahrd.utils.WebUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ExecutionException;

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


    /**
     * 获取当前登录对象
     *
     * @return
     */
    public static ExamUserInfo getUserInfo() {
        HttpServletRequest request = WebUtils.getRequest();
        String enName = "";
        String userInfoJSON = CookieUtils.getCookieValue(request, CookieConstants.USER_INFO_KEY);
        if (StringUtils.isNotBlank(userInfoJSON)) {
            VisitorVo visitor = JSON.parseObject(userInfoJSON, VisitorVo.class);
            enName = visitor.getEnName();
        }
        if (StringUtils.isNotBlank(enName)) {
            LoadingCacheUtil loadingCacheUtil = LoadingCacheUtil.getInstance();
            try {
                String userInfo = loadingCacheUtil.get(CookieConstants.USER_INFO_OBJ + enName, String.class);
                return JSON.parseObject(userInfo, ExamUserInfo.class);
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
