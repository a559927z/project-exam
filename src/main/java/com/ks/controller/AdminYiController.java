package com.ks.controller;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ks.dto.ExamQuestionBank;
import com.ks.dto.ExamQuestionBankReportDto;
import com.ks.service.impl.ExamQuestionBankService;
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
 * @DATE 2018年09月14日 00:48
 * @Verdion 1.0 版本
 * ${tags}
 */
@RequestMapping("/admin/ya")
@Controller
public class AdminYiController {


    @Autowired
    private ExamQuestionBankService examQuestionBankService;

    /**
     * http://localhost:8080/exam/admin/ya/index
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "admin/yaAdmin";
    }


    /**
     * http://localhost:8080/exam/admin/ya/queryAllQuestionBank
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryAllQuestionBank")
    public Map<String, Object> queryAllQuestionBank() {
        List<ExamQuestionBankReportDto> rsList = Lists.newArrayList();
        List<ExamQuestionBank> examQuestionBankList = examQuestionBankService.queryAllQuestionBank();
//        Lists.transform(examQuestionBankList, new Function<ExamQuestionBank, String>() {
//            @Override
//            public String apply(ExamQuestionBank input) {
//                return null;
//            }
//        });
        Map<String, Object> rsMap = Maps.newHashMap();
        examQuestionBankList.forEach(n -> {
            String questionBankId = n.getQuestionBankId();
            Object o = rsMap.get(questionBankId);
            if (null == o) {
                ExamQuestionBankReportDto dto = new ExamQuestionBankReportDto();
                dto.setQuestionBankId(n.getQuestionBankId());
                dto.setQuestionBankName(n.getQuestionBankName());
                List<ExamQuestionBankReportDto> report = examQuestionBankService.findReport(questionBankId);
                report.forEach(j -> {
                    dto.setQuestionNum(j.getQuestionNum());
                    dto.setType(j.getType());
                });
                rsList.add(dto);
                rsMap.put(questionBankId, dto);
            }
        });
        return rsMap;
    }

}