package com.ks.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.ks.constants.QuestionBankConstants;
import com.ks.dao.ExamQuestionBankMapper;
import com.ks.dao.ExamTrueAnswerMapper;
import com.ks.dto.ExamQuestionBank;
import com.ks.dto.ExamQuestionBankDto;
import com.ks.dto.ExamTrueAnswer;
import com.ks.service.UploadService;
import net.chinahrd.utils.Identities;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private ExamTrueAnswerMapper examTrueAnswerMapper;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertSelective(ExamQuestionBankDto dto) {

        int rs = 0;
        try {
            ExamQuestionBank o = new ExamQuestionBank();
            BeanUtils.copyProperties(dto, o);

            // 题目
            rs += examQuestionBankMapper.insertSelective(o);

            // 答案
            List<ExamTrueAnswer> examTrueAnswerList = processTrueAnswer(o);
            examTrueAnswerList.forEach(n -> examTrueAnswerMapper.insertSelective(n));

        } catch (Exception e) {
            throw e;
        }
        return rs;
    }

    private List<ExamTrueAnswer> processTrueAnswer(ExamQuestionBank entry) {
        List<ExamTrueAnswer> rs = Lists.newArrayList();
        List<String> trueAnswerList =
                Lists.newArrayList(Splitter.on(QuestionBankConstants.ANSWER_TRUE_PATTERN).omitEmptyStrings().trimResults().split(entry.getTrueAnswer()));

        for (int i = 0; i < trueAnswerList.size(); i++) {
            ExamTrueAnswer dto = new ExamTrueAnswer();
            dto.setQuestionBankId(entry.getQuestionBankId());
            dto.setTrueAnswerId(Identities.uuid2());
            dto.setTrueAnswer(trueAnswerList.get(i));
            dto.setSort(i);
            rs.add(dto);
        }
        return rs;
    }


}
