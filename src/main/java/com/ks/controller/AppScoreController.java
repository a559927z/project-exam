package com.ks.controller;

import com.ks.constants.CookieConstants;
import com.ks.service.AppAnswerService;
import com.ks.service.AppScoreService;
import com.ks.utils.CookieUtils;
import com.ks.utils.StringUtil;
import com.ks.vo.ScoreVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/app/score")
@Controller
public class AppScoreController {

    @Autowired
    private AppScoreService appScoreService;

    /**
     * http://localhost:8080/exam/app/score/toIndex
     *
     * @return
     */
    @RequestMapping("/toIndex")
    public String toScore(String questionBankId, Model model) {
        model.addAttribute("questionBankId", questionBankId);
        return "app/scoreApp";
    }


    /**
     * 计算成绩
     *
     * @return
     */
    @GetMapping
    @ResponseBody
    @RequestMapping("/calcScore")
    public List<ScoreVo> calcScore(String questionBankId,
                                   HttpServletRequest request) {
        String enName = CookieUtils.getCookieValue(request, CookieConstants.USER_INFO_KEY);
        return appScoreService.calcScore(questionBankId, enName);
    }

    /**
     * 交卷-保存
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveScore")
    public Boolean saveScore(@RequestParam("idList[]") List<String> idList, String questionBankId,
                             HttpServletRequest request) {
        String enName = CookieUtils.getCookieValue(request, CookieConstants.USER_INFO_KEY);
        if (CollectionUtils.isEmpty(idList)) {
            return false;
        }

        try {
            appScoreService.saveScore(idList, questionBankId, enName);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
