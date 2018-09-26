package com.ks.controller;

import com.google.common.collect.Maps;
import com.ks.dao.ExamQuestionBankYaMapper;
import com.ks.dao.ExamUserAnswerYaMapper;
import com.ks.dto.ExamQuestionBankYa;
import com.ks.dto.ExamQuestionBankYaExample;
import com.ks.dto.ExamUserAnswerYa;
import com.ks.dto.ExamUserAnswerYaExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

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
@RequestMapping("/app/my")
@Controller
public class AppMyController {


    @Autowired
    private ExamQuestionBankYaMapper examQuestionBankYaMapper;
    @Autowired
    private ExamUserAnswerYaMapper examUserAnswerYaMapper;


    /**
     * http://localhost:8080/exam/app/my/toIndex
     *
     * @return
     */
    @RequestMapping("/toIndex")
    public String toYaTi() {
        return "app/myApp";
    }

}
