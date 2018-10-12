package com.ks.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Splitter;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import com.ks.constants.QuestionBankConstants;
import com.ks.constants.QuestionBankTypeEnum;
import com.ks.dao.ExamQuestionBankAnswerMapper;
import com.ks.dao.ExamQuestionBankScoreMapper;
import com.ks.dao.ExamUserAnswerYaMapper;
import com.ks.dto.*;
import com.ks.service.AppScoreService;
import com.ks.utils.cache.LoadingCacheUtil;
import lombok.extern.slf4j.Slf4j;
import net.chinahrd.utils.Identities;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Title: ${type_name} <br/>
 * <p>
 * Description: <br/>
 *
 * @author jxzhang
 * @DATE 2018年10月12日 15:41
 * @Verdion 1.0 版本
 * ${tags}
 */
@Slf4j
@Service
public class AppScoreServiceImpl implements AppScoreService {

    @Autowired
    private ExamQuestionBankScoreMapper examQuestionBankScoreMapper;

    @Autowired
    private ExamQuestionBankAnswerMapper examQuestionBankAnswerMapper;

    @Autowired
    private ExamUserAnswerYaMapper examUserAnswerYaMapper;


    /**
     * 入库 用户答案
     *
     * @param idList
     * @param questionBankId
     * @return
     * @throws ExecutionException
     */
    private List<ExamUserAnswerYa> saveScore(List<String> idList, String questionBankId, String enName) {
//        PublicUserInfo userInfo = null;
//        try {
//            userInfo = LoadingCacheUtil.getInstance().get("enName", PublicUserInfo.class);
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        String enName = userInfo.getEnName();


        List<ExamUserAnswerYa> userAnswerYaList = Lists.newArrayList();
        idList.forEach(i -> {
            ArrayList<String> strings = Lists.newArrayList(Splitter.on("@").omitEmptyStrings().trimResults().split(i));
            String selectAnswer = strings.get(0);
            String questionId = strings.get(1);

            ExamUserAnswerYa dto = new ExamUserAnswerYa();
            dto.setUserAnswerId(Identities.uuid2());
            dto.setUserId(enName);
            dto.setQuestionBankId(questionBankId);
            dto.setQuestionId(questionId);
            dto.setUserAnswer(selectAnswer);

            userAnswerYaList.add(dto);
            examUserAnswerYaMapper.insertSelective(dto);
        });
        return userAnswerYaList;
    }


    @Override
    public void calcScore(List<String> idList, String questionBankId, String enName) {
        if (CollectionUtils.isEmpty(idList)) {
            log.info("没有答题");
        }
        List<ExamUserAnswerYa> userAnswerYaList = saveScore(idList, questionBankId, enName);
        //  用户答案的多值map, key: qId, value: List<ExamUserAnswerYa>
        Multimap<String, ExamUserAnswerYa> uaYaMultiMap = ArrayListMultimap.create();
        userAnswerYaList.forEach(n -> {
            String questionId = n.getQuestionId();
            uaYaMultiMap.put(questionId, n);
        });


        ExamQuestionBankAnswerExample qbAnswerExample = new ExamQuestionBankAnswerExample();
        qbAnswerExample.createCriteria().andQuestionBankIdEqualTo(questionBankId);
        List<ExamQuestionBankAnswer> qbAnswerList = examQuestionBankAnswerMapper.selectByExample(qbAnswerExample);
        //  答案的多值map, key: qId, value: List<ExamQuestionBankAnswer>
        Multimap<String, ExamQuestionBankAnswer> qbAnswerMultiMap = ArrayListMultimap.create();
        qbAnswerList.forEach(n -> {
            String questionId = n.getQuestionId();
            qbAnswerMultiMap.put(questionId, n);
        });


        Multiset<String> keys = uaYaMultiMap.keys();
        // 获取用户多值Map里的questionId
        for (String questionId : keys) {
            Collection<ExamQuestionBankAnswer> qAnswerList = qbAnswerMultiMap.get(questionId);
            Collection<ExamUserAnswerYa> qUaYaList = uaYaMultiMap.get(questionId);

            // 每个key对应一个List, 用户答案和真答案匹配
            for (ExamUserAnswerYa uaYa : qUaYaList) {
                boolean yes = false;

                String uaABCD = uaYa.getUserAnswer();
                for (ExamQuestionBankAnswer qbAnswer : qAnswerList) {
                    String answerABCD = qbAnswer.getAnswerno();

                    if (uaABCD.equals(answerABCD)) {

                    }

                }

            }


        }


        idList.forEach(i -> {
            ArrayList<String> strings = Lists.newArrayList(Splitter.on("@").omitEmptyStrings().trimResults().split(i));
            String selectAnswer = strings.get(0);
            String questionId = strings.get(1);

            // TODO
            qbAnswerList.forEach(n -> {

                boolean isYourYes = false;
                if (n.getQuestionId().equals(questionId)) {
                    if (n.getAnswerno().equals(selectAnswer)) {
                        isYourYes = true;
                    } else {
                        isYourYes = false;
                    }
                }

                String type = n.getType();
                if (QuestionBankTypeEnum.SINGLE_QUESTION.equals(type)) {

                } else if (QuestionBankTypeEnum.SINGLE_QUESTION.equals(type)) {

                } else {

                }
            });

        });

        // 得分
        ExamQuestionBankScoreExample qbScoreExample = new ExamQuestionBankScoreExample();
        qbScoreExample.createCriteria().andQuestionBankIdEqualTo(questionBankId);
        List<ExamQuestionBankScore> examQuestionBankScoreList = examQuestionBankScoreMapper.selectByExample(qbScoreExample);
        if (CollectionUtils.isEmpty(examQuestionBankScoreList)) {
            log.info("没有设置得分");
        }
        ExamQuestionBankScore examQuestionBankScore = examQuestionBankScoreList.get(0);


    }
}
