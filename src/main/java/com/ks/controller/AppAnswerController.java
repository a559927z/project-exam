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
@RequestMapping("/app/answer")
@Controller
public class AppAnswerController {


    /**
     * http://localhost:8080/exam/app/answer/toIndex
     *
     * @return
     */
    @RequestMapping("/toIndex")
    public String toAnswer() {
        return "app/answerApp";
    }

}
