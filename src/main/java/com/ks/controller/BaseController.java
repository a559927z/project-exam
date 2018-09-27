package com.ks.controller;

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

}