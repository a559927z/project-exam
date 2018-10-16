package com.ks.service.impl;

import com.google.common.collect.Lists;
import com.ks.constants.QuestionBankConstants;
import com.ks.dao.ExamQuestionBankAnswerMapper;
import com.ks.dao.ExamQuestionBankMapper;
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
    private ExamQuestionBankAnswerMapper examQuestionBankAnswerMapper;

    @Override
    public List<AnswerVo> getData(ScreenTiDto screenTiDto) {
        //TODO
        boolean notDo = screenTiDto.isNotDo();
        String questionBankId = screenTiDto.getQuestionBankId();

        // query qbList
        ExamQuestionBankExample qbExample = new ExamQuestionBankExample();
        qbExample.createCriteria().andQuestionBankIdEqualTo(questionBankId);
        qbExample.setOrderByClause("type asc");
        List<ExamQuestionBank> examQuestionBankList = examQuestionBankMapper.selectByExample(qbExample);
        if (CollectionKit.isEmpty(examQuestionBankList)) {
            log.error("题库被删除了");
        }

        ExamQuestionBankAnswerExample qbAnswerExample = new ExamQuestionBankAnswerExample();
        qbAnswerExample.createCriteria().andQuestionBankIdEqualTo(questionBankId);
        List<ExamQuestionBankAnswer> qbAnswerList = examQuestionBankAnswerMapper.selectByExample(qbAnswerExample);

        List<AnswerVo> voList = Lists.newArrayList();
        examQuestionBankList
                .forEach(i -> {
                    String questionId = i.getQuestionId();
                    AnswerVo vo = new AnswerVo();
                    vo.setQuestionBankId(questionBankId);
                    vo.setQuestionId(questionId);
                    vo.setJieXi(i.getJieXi());
                    vo.setType(i.getType());
                    vo.setTitle(i.getTitle());
                    List<String> answerList = Lists.newArrayList();
                    List<String> trueAnswerList = Lists.newArrayList();
                    qbAnswerList.forEach(j -> {
                        if (questionId.equals(j.getQuestionId())) {
                            answerList.add(j.getAnswerno() + "@" + j.getAnswer());
                            if (j.getIsanswer()) {
                                trueAnswerList.add(j.getAnswerno());
                            }
                        }
                    });
                    vo.setTrueAnswer(trueAnswerList);
                    vo.setAnswer(answerList);
                    voList.add(vo);
                });

        // TODO cach
//        voList;

        // filter type
        List<AnswerVo> typeQbList = Lists.newArrayList();
        int typeInt = screenTiDto.getType();
        if (typeInt == All) {
            typeQbList = voList;
        } else {
            String type = typeInt + "";
            for (AnswerVo vo : voList) {
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

        List<AnswerVo> rsList2 = Lists.newArrayList();
        for (int i = 0; i < rsList.size(); i++) {
            AnswerVo vo = rsList.get(i);
            vo.setNo(i + 1);
            rsList2.add(vo);
        }
        return rsList2;
    }

}
