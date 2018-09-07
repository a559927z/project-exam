package com.ks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/admin")
@Controller
public class AdminController {


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
     * @return
     */
    @RequestMapping("/validate")
    @PostMapping
    public String validate(Model model,
                        @RequestParam("accessId") String userName,
                        @RequestParam("password") String password) {

        model.addAttribute("access", userName);
        model.addAttribute("password", password);
        // TODO check db
        if (1 != 1) {
            return REDIRECT_TO_LOGIN;
        } else {
            return REDIRECT_TO_INDEX;
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

}