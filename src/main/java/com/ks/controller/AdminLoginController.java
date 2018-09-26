package com.ks.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/admin")
@Controller
public class AdminLoginController {


    /**
     * 重定向
     */
    private final String REDIRECT_TO_LOGIN = "redirect:toLogin";
    private final String REDIRECT_TO_INDEX = "redirect:toIndex";

    @RequestMapping("/")
    public String root() {
        return REDIRECT_TO_LOGIN;
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

    /**
     * @param model
     * @param userName admin
     * @param password 123456
     * @return
     */
    @RequestMapping("/validate")
    @PostMapping
    public String validate(Model model,
                           @RequestParam("accessId") String userName,
                           @RequestParam("password") String password) {


        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        try {
            subject.login(token);
            model.addAttribute("access", userName);
            model.addAttribute("password", password);
            return REDIRECT_TO_INDEX;
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMsg", "用户名或密码错误!");
            return REDIRECT_TO_LOGIN;
        }

    }

    /**
     * http://localhost:8080/exam/admin/toLogin
     *
     * @return
     */
    @RequestMapping("/toIndex")
    public String toIndex() {
        return "admin/index";
    }

    /**
     * js提交btn事件：提交过来请求
     *
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
        }
        return REDIRECT_TO_LOGIN;
    }

}