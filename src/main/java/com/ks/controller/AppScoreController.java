package com.ks.controller;

import com.ks.constants.CookieConstants;
import com.ks.service.AppAnswerService;
import com.ks.service.AppScoreService;
import com.ks.utils.CookieUtils;
import com.ks.utils.StringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String toScore() {
        return "app/scoreApp";
    }


    /**
     * @return
     */
    @ResponseBody
    @RequestMapping("/calcScore")
    public String calcScore(@RequestParam("idList[]") List<String> idList, String questionBankId,
                            HttpServletRequest request) {
        String enName = CookieUtils.getCookieValue(request, CookieConstants.USER_INFO_KEY);
        if (CollectionUtils.isEmpty(idList)) {
            return "xxx";
        }
        appScoreService.calcScore(idList, questionBankId, enName);

        return "";
    }


}
