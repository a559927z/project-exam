package com.ks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/app/screen")
@Controller
public class AppScreenController {


    /**
     * http://localhost:8080/exam/app/screen/toIndex
     *
     * @return
     */
    @GetMapping
    @RequestMapping("/toIndex")
    public String toScreenTi(String questionBankId, Model model) {
        model.addAttribute("questionBankId", questionBankId);
        return "app/screenTiApp";
    }


}
