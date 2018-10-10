package com.ks.service.impl;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.ks.constants.QuestionBankConstants;
import com.ks.dao.ExamQuestionBankMapper;
import com.ks.dao.ExamQuestionBankTrueAnswerMapper;
import com.ks.dao.ExamQuestionBankYaMapper;
import com.ks.dto.*;
import com.ks.service.AppAnswerService;
import com.ks.utils.StringUtil;
import com.ks.vo.AnswerVo;
import lombok.extern.slf4j.Slf4j;
import net.chinahrd.utils.CollectionKit;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Title: ${type_name} <br/>
 * <p>
 * Description: <br/>
 *
 * @author jxzhang
 * @DATE 2018年10月10日 10:32
 * @Verdion 1.0 版本
 * ${tags}
 */
@Slf4j
@Service
public class AppAnswerServiceImpl implements AppAnswerService {

    final int All = 9999;

    @Autowired
    private ExamQuestionBankMapper examQuestionBankMapper;
    @Autowired
    private ExamQuestionBankYaMapper examQuestionBankYaMapper;
    @Autowired
    private ExamQuestionBankTrueAnswerMapper examQuestionBankTrueAnswerMapper;

    @Override
    public List<AnswerVo> getData(ScreenTiDto screenTiDto) {
        boolean notDo = screenTiDto.isNotDo();
        String questionBankId = screenTiDto.getQuestionBankId();

        // query qbList
        ExamQuestionBankExample qbYaExample = new ExamQuestionBankExample();
        qbYaExample.createCriteria().andQuestionBankIdEqualTo(questionBankId);
        List<ExamQuestionBank> examQuestionBankList = examQuestionBankMapper.selectByExample(qbYaExample);
        if (CollectionKit.isEmpty(examQuestionBankList)) {
            log.error("题库被删除了");
        }

        ExamQuestionBankTrueAnswerExample qbTrueAnswerExample = new ExamQuestionBankTrueAnswerExample();
        qbTrueAnswerExample.createCriteria().andQuestionBankIdEqualTo(questionBankId);
        List<ExamQuestionBankTrueAnswer> qbTrueAnswerList = examQuestionBankTrueAnswerMapper.selectByExample(qbTrueAnswerExample);

        List<AnswerVo> voList = Lists.newArrayList();
        examQuestionBankList.forEach(i -> {
            String questionId = i.getQuestionId();
            AnswerVo vo = new AnswerVo();
            vo.setQuestionBankId(questionBankId);
            vo.setQuestionId(questionId);
            vo.setJieXi(i.getJieXi());
            vo.setType(i.getType());
            vo.setTitle(i.getTitle());
            String answer = i.getAnswer();
            vo.setAnswer(processAnswer(answer));
            qbTrueAnswerList.forEach(j -> {
                if (questionId.equals(j.getQuestionId())) {
                    vo.setTrueAnswer(j.getTrueAnswer());
                }
            });
            voList.add(vo);
        });


        // TODO cach
//        examQuestionBankList;

        // filter type
        List<AnswerVo> typeQbList = Lists.newArrayList();
        int typeInt = screenTiDto.getType();
        if (typeInt == All) {
            typeQbList = voList;
        } else {
            String type = typeInt + "";
            for (AnswerVo vo : typeQbList) {
                if (vo.getType().equals(type)) {
                    typeQbList.add(vo);
                }
            }
        }

        // filter total
        List<AnswerVo> filterList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(typeQbList)) {
            filterList = typeQbList;
        } else {
            filterList = voList;
        }

        List<AnswerVo> rsList = Lists.newArrayList();
        int total = screenTiDto.getTotal();
        if (total == All) {
            rsList = filterList;
        } else {
            for (int i = 0; i < total; i++) {
                rsList.add(filterList.get(i));
            }
        }
        return rsList;
    }

    /**
     * @param answer
     * @return
     */
    private List<String> processAnswer(String answer) {
        String replace = answer.replace(QuestionBankConstants.ANSWER_PATTERN, "@")
                .replace(QuestionBankConstants.ANSWER_TRUE_PATTERN, "@");
        String[] split = replace.split("@");
        List<String> rs = Lists.newArrayList();
        for (int i = 0; i < split.length; i++) {
            if (StringUtil.isNotBlank(split[i])) {
                rs.add(split[i]);
            }
        }
        return rs;
    }
}
