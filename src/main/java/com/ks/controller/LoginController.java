package com.ks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Title: ${type_name} <br/>
 * <p>
 * Description: <br/>
 *
 * @author jxzhang
 * @DATE 2018年09月05日 23:55
 * @Verdion 1.0 版本
 * ${tags}
 */
@RequestMapping("/admin")
@Controller
public class LoginController {

    /**
     * @return
     */
    @RequestMapping("/login")
    @PostMapping
    public String login(Model model,
                        @RequestParam("accessId") String userName,
                        @RequestParam("password") String password) {

        model.addAttribute("access", userName);
        model.addAttribute("password", password);
        return "admin/index";
    }
}
