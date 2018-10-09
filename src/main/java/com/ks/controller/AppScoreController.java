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
@RequestMapping("/app/score")
@Controller
public class AppScoreController {


    /**
     * http://localhost:8080/exam/app/score/toIndex
     *
     * @return
     */
    @RequestMapping("/toIndex")
    public String toScore() {
        return "app/scoreApp";
    }


}
