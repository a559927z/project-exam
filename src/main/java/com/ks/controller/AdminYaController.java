package com.ks.controller;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.ks.dto.ExamQuestionBank;
import com.ks.dto.ExamQuestionBankReportDto;
import com.ks.dto.KVItemDto;
import com.ks.service.impl.ExamQuestionBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
public class AdminYaController {


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

        Set<String> questionBankIdSet = Sets.newHashSet(examQuestionBankList.stream().map(n -> n.getQuestionBankId()).collect(Collectors.toList()));

        Map<String, Object> rsMap = Maps.newHashMap();
        questionBankIdSet.forEach(n -> {
            List<ExamQuestionBankReportDto> report = examQuestionBankService.findReport(n);
            rsMap.put(n, report);
        });
        return rsMap;
    }

}