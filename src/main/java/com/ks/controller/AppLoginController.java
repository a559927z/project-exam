package com.ks.controller;

import com.google.common.collect.Maps;
import com.ks.dao.ExamQuestionBankYaMapper;
import com.ks.dao.ExamUserAnswerYaMapper;
import com.ks.dao.PublicLoginMapper;
import com.ks.dto.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
@RequestMapping("/app/login")
@Controller
public class AppLoginController {


    @Autowired
    private PublicLoginMapper publicLoginMapper;


    /**
     * http://localhost:8080/exam/admin/ya/queryYaTi
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/")
    public Map<String, Object> login(HttpServletRequest request, String accessId, String password) {
        PublicLoginExample example = new PublicLoginExample();
        example.createCriteria().andAccessIdEqualTo(accessId).andPasswordEqualTo(password);
        List<PublicLogin> publicLogins = publicLoginMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(publicLogins)) {
            HttpSession session = request.getSession();
        }
        return null;
    }

    /**
     * http://localhost:8080/exam/admin/ya/queryYaTi
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveLogin")
    public Map<String, Object> saveLogin(String userId, String courseId) {
        Map<String, Object> rsMap = Maps.newHashMap();
        return rsMap;
    }


}
