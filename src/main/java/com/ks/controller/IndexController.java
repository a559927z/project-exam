package com.ks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Title: ${type_name} <br/>
 * <p>
 * Description: <br/>
 *
 * @author jxzhang
 * @DATE 2018年09月17日 22:43
 * @Verdion 1.0 版本
 * ${tags}
 */
@RequestMapping("/base")
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String login() {
        return "index";
    }


}
