package com.ks.controller;

import com.ks.constants.CookieConstants;
import com.ks.constants.EisWebContext;
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
public class AppScoreController extends BaseController {

    @Autowired
    private AppScoreService appScoreService;

    /**
     * http://localhost:8080/exam/app/score/toIndex
     *
     * @return
     */
    @RequestMapping("/toIndexForYa")
    public String toIndexForYa(String questionBankId, Model model) {
        model.addAttribute("questionBankId", questionBankId);
        return "app/scoreApp";
    }

    /**
     * 跳转计算成绩 - 卷
     *
     * @return
     */
    @RequestMapping("/toIndexForRoll")
    public String toIndexForRoll(String rollId, Model model) {
        model.addAttribute("rollId", rollId);
        return "app/rollScoreApp";
    }


    /**
     * 计算成绩 - 押题
     *
     * @return
     */
    @GetMapping
    @ResponseBody
    @RequestMapping("/calcYaScore")
    public List<ScoreVo> calcYaScore(String questionBankId,
                                     HttpServletRequest request) {
        String userId = EisWebContext.getUserInfo().getAccount();
        return appScoreService.calcYaScore(questionBankId, userId);
    }

    /**
     * 计算成绩 - 卷
     *
     * @return
     */
    @GetMapping
    @ResponseBody
    @RequestMapping("/calcRollScore")
    public List<ScoreVo> calcRollScore(String rollId,
                                       HttpServletRequest request) {
        String userId = EisWebContext.getUserInfo().getAccount();
        return appScoreService.calcRollScore(rollId, userId);
    }


    /**
     * 交卷-保存
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveScore")
    public Boolean saveScore(@RequestParam(value = "idList[]", required = false) List<String> idList, String questionBankId,
                             HttpServletRequest request) {
        String userId = EisWebContext.getUserInfo().getAccount();
        // 没答题，返回true，后台不处理，前台跳转页面。
        if (CollectionUtils.isEmpty(idList)) {
            return true;
        }
        try {
            appScoreService.saveScore(idList, questionBankId, userId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
