package com.ks.service.impl;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.ks.constants.EisWebContext;
import com.ks.constants.QuestionBankConstants;
import com.ks.dao.ExamQuestionBankMapper;
import com.ks.dao.ExamQuestionBankTrueAnswerMapper;
import com.ks.dto.ExamQuestionBank;
import com.ks.dto.ExamQuestionBankTrueAnswer;
import com.ks.service.UploadService;
import net.chinahrd.utils.Identities;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Title: ${type_name} <br/>
 * <p>
 * Description: <br/>
 *
 * @author jxzhang
 * @DATE 2018年09月10日 14:11
 * @Verdion 1.0 版本
 * ${tags}
 */
@Service("uploadService")
public class UploadServiceImpl implements UploadService {


    @Autowired
    private ExamQuestionBankMapper examQuestionBankMapper;

    @Autowired
    private ExamQuestionBankTrueAnswerMapper examQuestionBankTrueAnswerMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertSelective(ExamQuestionBank dto) {

        int rs = 0;
        try {
            ExamQuestionBank o = new ExamQuestionBank();
            BeanUtils.copyProperties(dto, o);
            Date date = new Date();
            o.setCreatedBy(EisWebContext.getCurrentUserId());
            o.setUpdatedBy(EisWebContext.getCurrentUserId());
            o.setCreatedDate(date);
            o.setUpdatedDate(date);

            // 题目
            rs += examQuestionBankMapper.insertSelective(o);

            // 答案
            List<ExamQuestionBankTrueAnswer> examTrueAnswerList = processTrueAnswer(o);
            examTrueAnswerList.forEach(n -> examQuestionBankTrueAnswerMapper.insertSelective(n));

        } catch (Exception e) {
            throw e;
        }
        return rs;
    }

    private List<ExamQuestionBankTrueAnswer> processTrueAnswer(ExamQuestionBank entry) {
        List<ExamQuestionBankTrueAnswer> rs = Lists.newArrayList();
        List<String> trueAnswerList =
                Lists.newArrayList(Splitter.on(QuestionBankConstants.ANSWER_TRUE_PATTERN).omitEmptyStrings().trimResults().split(entry.getTrueAnswer()));

        for (int i = 0; i < trueAnswerList.size(); i++) {
            ExamQuestionBankTrueAnswer dto = new ExamQuestionBankTrueAnswer();
            dto.setQuestionId(entry.getQuestionId());
            dto.setQuestionBankId(entry.getQuestionBankId());
            dto.setTrueAnswerId(Identities.uuid2());
            dto.setTrueAnswer(trueAnswerList.get(i));
            dto.setSort(i);
            rs.add(dto);
        }
        return rs;
    }


}
