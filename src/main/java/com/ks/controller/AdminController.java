package com.ks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
@Controller
public class AdminController {


    /**
     * 重定向列表页
     */
    private final String REDIRECT_LOGIN = "redirect:login";

    @RequestMapping("/")
    public String index() {
//        login()
        return REDIRECT_LOGIN;
    }


    /**
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        return "admin/login";
    }


}