package com.ks.controller;

import com.ks.constants.EisWebContext;
import com.ks.dto.ExamUserAnswerYa;
import com.ks.dto.ExamUserInfo;
import com.ks.dto.ScreenTiDto;
import com.ks.service.AppAnswerService;
import com.ks.vo.AnswerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
public class AppAnswerController extends BaseController {

    @Autowired
    private AppAnswerService appAnswerService;

    /**
     * http://localhost:8080/exam/app/answer/toIndex
     *
     * @return
     */
    @RequestMapping("/toIndex")
    public String toAnswer(String questionBankId, int type, int total, boolean notDo,
                           Model model) {

        model.addAttribute("questionBankId", questionBankId);
        model.addAttribute("type", type);
        model.addAttribute("total", total);
        model.addAttribute("notDo", notDo);
        return "app/answerApp";
    }


    /**
     * 获取押题库
     * 考前押题 --> 选题 --> 押题库
     *
     * @param screenTiDto
     * @return
     */
    @ResponseBody
    @PostMapping
    @RequestMapping("/getData")
    public List<AnswerVo> getData(ScreenTiDto screenTiDto) {
        return appAnswerService.getData(screenTiDto);
    }

    /**
     * 考前押题 --> 选题 --> 押题库 --> 用户已答题答案
     *
     * @param questionBankId
     * @return
     */
    @ResponseBody
    @GetMapping
    @RequestMapping("/queryUserAnswer")
    public List<ExamUserAnswerYa> queryUserAnswer(String questionBankId) {
        String userId = EisWebContext.getUserInfo().getAccount();
        return appAnswerService.queryUserAnswer(questionBankId, userId);
    }


}
