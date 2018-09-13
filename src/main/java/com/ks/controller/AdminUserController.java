package com.ks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Title: ${type_name} <br/>
 * <p>
 * Description: <br/>
 *
 * @author jxzhang
 * @DATE 2018年09月14日 00:48
 * @Verdion 1.0 版本
 * ${tags}
 */
@RequestMapping("/admin/user")
@Controller
public class AdminUserController {


    /**
     * http://localhost:8080/exam/admin/user/index
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "admin/userAdmin";
    }

}