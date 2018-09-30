package com.ks.service.impl;

import com.ks.dao.ExamQuestionBankYaMapper;
import com.ks.dao.ExamUserAnswerYaMapper;
import com.ks.dto.ExamQuestionBankYa;
import com.ks.dto.ExamQuestionBankYaExample;
import com.ks.dto.ExamUserAnswerYa;
import com.ks.dto.ExamUserAnswerYaExample;
import com.ks.service.AppYaService;
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


    @Override
    public List<ExamUserAnswerYa> queryYaTiByUserId(String enName, String courseId) {
        ExamUserAnswerYaExample example = new ExamUserAnswerYaExample();
        example.createCriteria().andUserIdEqualTo(enName).andCourseIdEqualTo(courseId);
        List<ExamUserAnswerYa> yaTiList = examUserAnswerYaMapper.selectByExample(example);

        // 第一次就插入
        if (yaTiList.size() == 0) {
            ExamQuestionBankYaExample qbYaExample = new ExamQuestionBankYaExample();
            example.createCriteria().andQuestionBankIdEqualTo(courseId);
            List<ExamQuestionBankYa> qbYaList = examQuestionBankYaMapper.selectByExample(qbYaExample);

            qbYaList.forEach(n -> {
                ExamUserAnswerYa dto = new ExamUserAnswerYa();
                dto.setUserAnswerId(Identities.uuid2());
                dto.setUserId(enName);
                dto.setQuestionBankId(n.getQuestionBankId());
                dto.setCourseId(n.getCourseId());
                examUserAnswerYaMapper.insertSelective(dto);
            });
        }
        return yaTiList;
    }
}