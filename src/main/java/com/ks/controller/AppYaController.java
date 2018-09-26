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
@RequestMapping("/app/ya")
@Controller
public class AppYaController {


    @Autowired
    private ExamQuestionBankYaMapper examQuestionBankYaMapper;
    @Autowired
    private ExamUserAnswerYaMapper examUserAnswerYaMapper;


    /**
     * http://localhost:8080/exam/admin/ya/queryYaTi
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryYaTiByUserId")
    public Map<String, Object> queryYaTiByUserId(String userId, String courseId) {
        // TODO userId = superAdmin

        ExamUserAnswerYaExample example = new ExamUserAnswerYaExample();
        example.createCriteria().andUserIdEqualTo(userId).andCourseIdEqualTo(courseId);
        List<ExamUserAnswerYa> yaTiList = examUserAnswerYaMapper.selectByExample(example);

        if (yaTiList.size() <= 0) {

            ExamQuestionBankYaExample qbYaExample = new ExamQuestionBankYaExample();
            example.createCriteria().andQuestionBankIdEqualTo(courseId);
            List<ExamQuestionBankYa> qbYaList = examQuestionBankYaMapper.selectByExample(qbYaExample);


            qbYaList.forEach(n -> {
                String questionBankId = n.getQuestionBankId();
                ExamUserAnswerYa dto = new ExamUserAnswerYa();
                dto.setUserAnswer(userId);
                dto.setQuestionBankId(questionBankId);
                examUserAnswerYaMapper.insertSelective(dto);
            });


        }

        Map<String, Object> rsMap = Maps.newHashMap();
        rsMap.put("queryYaTi", yaTiList);
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
