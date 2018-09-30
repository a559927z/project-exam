package com.ks.controller;

import com.google.common.collect.Maps;
import com.ks.constants.CookieConstants;
import com.ks.constants.UrlConstants;
import com.ks.dao.ExamQuestionBankYaMapper;
import com.ks.dao.ExamUserAnswerYaMapper;
import com.ks.dto.ExamQuestionBankYa;
import com.ks.dto.ExamQuestionBankYaExample;
import com.ks.dto.ExamUserAnswerYa;
import com.ks.dto.ExamUserAnswerYaExample;
import com.ks.service.AppYaService;
import com.ks.utils.CookieUtils;
import net.chinahrd.utils.Identities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/app/ya")
@Controller
public class AppYaController {


    @Autowired
    private ExamQuestionBankYaMapper examQuestionBankYaMapper;

    @Autowired
    private ExamUserAnswerYaMapper examUserAnswerYaMapper;

    @Autowired
    private AppYaService appYaService;

    /**
     * http://localhost:8080/exam/admin/ya/queryYaTi
     * 押题管理列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryYaTiByUserId")
    public Map<String, Object> queryYaTiByUserId(HttpServletRequest request, String courseId) {
        String enName = CookieUtils.getCookieValue(request, CookieConstants.USER_INFO_KEY);

        List<ExamUserAnswerYa> yaTiList = appYaService.queryYaTiByUserId(enName, courseId);

        Map<String, Object> rsMap = Maps.newHashMap();
        rsMap.put("list", yaTiList);
        return rsMap;
    }

    /**
     * http://localhost:8080/exam/app/toIndex
     *
     * @return
     */
    @RequestMapping("/toIndex")
    public String toYaTi() {
        return "app/yaTiApp";
    }

}
