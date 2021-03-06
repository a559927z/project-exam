package com.ks.controller;

import com.alibaba.fastjson.JSON;
import com.ks.constants.CookieConstants;
import com.ks.utils.CookieUtils;
import com.ks.vo.VisitorVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;

public class BaseController {

    @Value("${ctx}")
    private String ctx;

    /**
     * http://loclahost:8080/exam
     * 被 path2()代替
     *
     * @param request
     * @return
     */
    @Deprecated
    public String path(HttpServletRequest request) {
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
        return basePath;
    }

    /**
     * http://loclahost:8080/exam
     *
     * @param request
     * @return
     */
    public String path2(HttpServletRequest request) {
        return ctx;
    }


    /**
     * 静态资源下
     *
     * @return
     * @throws FileNotFoundException
     */
    public String assetsPath() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:assets/");
        return file.getAbsolutePath();
    }


    /**
     * 静态资源下img
     *
     * @return
     * @throws FileNotFoundException
     */
    public String assetsImgPath() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:assets/img/");
        return file.getAbsolutePath();
    }

    /**
     * 获取访问对象
     *
     * @param request
     */
    public VisitorVo getVisitor(HttpServletRequest request) {
        String userInfoJSON = CookieUtils.getCookieValue(request, CookieConstants.USER_INFO_KEY);
        if (StringUtils.isNotBlank(userInfoJSON)) {
            VisitorVo visitor = JSON.parseObject(userInfoJSON, VisitorVo.class);
            return visitor;
        }
        return null;
    }

}