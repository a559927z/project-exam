package com.ks.service.impl;

import com.google.common.collect.Lists;
import com.ks.dao.ExamQuestionBankMapper;
import com.ks.dao.ExamQuestionBankYaMapper;
import com.ks.dao.ExamUserAnswerYaMapper;
import com.ks.dto.*;
import com.ks.service.AppYaService;
import com.ks.vo.ExamUserAnswerYaVo;
import net.chinahrd.utils.Identities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Title: ${type_name} <br/>
 * <p>
 * Description: <br/>
 *
 * @author jxzhang
 * @DATE 2018年09月30日 14:21
 * @Verdion 1.0 版本
 * ${tags}
 */
@Service("appYaService")
public class AppYaServiceImpl implements AppYaService {

    @Autowired
    private ExamQuestionBankYaMapper examQuestionBankYaMapper;

    @Autowired
    private ExamUserAnswerYaMapper examUserAnswerYaMapper;

    @Autowired
    private ExamQuestionBankMapper examQuestionBankMapper;


    /**
     * TODO，数据入库。user_answer_ya，是否考滤所有题目
     *
     * @param enName
     * @param courseId
     * @return
     */
    @Override
    public List<ExamUserAnswerYaVo> queryYaTiByUserId(String enName, String courseId) {
        ExamUserAnswerYaExample uaYaExample = new ExamUserAnswerYaExample();
        uaYaExample.createCriteria().andUserIdEqualTo(enName).andCourseIdEqualTo(courseId);
        List<ExamUserAnswerYa> uaYaList = examUserAnswerYaMapper.selectByExample(uaYaExample);

        // 第一次就插入，TODO 数据入库。user_answer_ya，是否考滤所有题目
        if (uaYaList.size() == 0) {
            ExamQuestionBankYaExample qbYaExample = new ExamQuestionBankYaExample();
            qbYaExample.createCriteria().andCourseIdEqualTo(courseId);
            List<ExamQuestionBankYa> qbYaList = examQuestionBankYaMapper.selectByExample(qbYaExample);

            qbYaList.forEach(n -> {
                ExamUserAnswerYa dto = new ExamUserAnswerYa();
                String pkId = Identities.uuid2();
                dto.setUserAnswerId(pkId);
                dto.setUserId(enName);
                dto.setQuestionBankId(n.getQuestionBankId());
                dto.setCourseId(n.getCourseId());
                examUserAnswerYaMapper.insertSelective(dto);

                uaYaList.add(dto);
            });
        }

        // 总数
        List<ExamUserAnswerYaVo> voList = Lists.newArrayList();
        uaYaList.forEach(n -> {
            String pkId = n.getUserAnswerId();
            String questionBankId = n.getQuestionBankId();
            // 题库
            ExamQuestionBankExample qbExample = new ExamQuestionBankExample();
            qbExample.createCriteria().andQuestionBankIdEqualTo(questionBankId);
            long total = examQuestionBankMapper.countByExample(qbExample);

            // 我答题 //TODO
            ExamUserAnswerYaExample yaExample = new ExamUserAnswerYaExample();
            yaExample.createCriteria().andQuestionBankIdEqualTo(questionBankId).andUserAnswerIsNotNull();
            long finishTotal = examUserAnswerYaMapper.countByExample(yaExample);

            ExamUserAnswerYaVo vo = new ExamUserAnswerYaVo();
            vo.setUserAnswerId(pkId);
            vo.setUserId(enName);
            vo.setQuestionBankId(questionBankId);
            vo.setTotal(total);
            vo.setFinishTotal(finishTotal);
            voList.add(vo);
        });
        return voList;
    }
}