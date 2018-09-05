package com.ks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
@Controller
public class AdminController {


    /**
     * 重定向列表页
     */
    private final String REDIRECT_LOGIN = "redirect:toLogin";

    @RequestMapping("/")
    public String index() {
        return REDIRECT_LOGIN;
    }


    /**
     * http://localhost:8080/exam/admin/toLogin
     *
     * @return
     */
    @RequestMapping("/toLogin")
    public String login() {
        return "admin/login";
    }


}