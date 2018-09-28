package com.ks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Title: ${type_name} <br/>
 * <p>
 * Description: <br/>
 *
 * @author jxzhang
 * @DATE 2018年09月17日 22:53
 * @Verdion 1.0 版本
 * ${tags}
 */
@RequestMapping("/app/home")
@Controller
public class AppHomeController {

    /**
     * 重定向
     */
    private final String REDIRECT_TO_HOME = "redirect:toIndex";

    @RequestMapping("/")
    public String root() {
        return REDIRECT_TO_HOME;
    }


    /**
     * http://localhost:8080/exam/app/toHome
     *
     * @return
     */
    @RequestMapping("/toIndex")
    public String index() {
        return "app/homeApp";
    }



}
