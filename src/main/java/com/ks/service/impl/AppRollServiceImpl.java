package com.ks.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.ks.constants.QuestionBankCourseEnum;
import com.ks.dao.ExamQuestionBankMapper;
import com.ks.dao.ExamQuestionBankYaMapper;
import com.ks.dao.ExamRollUserMapper;
import com.ks.dto.*;
import com.ks.service.AppRollService;
import com.ks.utils.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import net.chinahrd.utils.Identities;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Title: ${type_name} <br/>
 * <p>
 * Description: <br/>
 *
 * @author jxzhang
 * @DATE 2018年11月07日 00:17
 * @Verdion 1.0 版本
 * ${tags}
 */
@Slf4j
@Service
public class AppRollServiceImpl implements AppRollService {

    @Autowired
    private ExamRollUserMapper examRollUserMapper;

    @Autowired
    private ExamQuestionBankMapper examQuestionBankMapper;

    @Autowired
    private ExamQuestionBankYaMapper examQuestionBankYaMapper;

    @Override
    public String randomRoll(String courseId, String userId) {

        if (StringUtils.isBlank(courseId)) {
            return null;
        }

        List<ExamQuestionBank> qbSList = this.queryQbListByYaQbId(courseId, "1");
        List<ExamQuestionBank> qbMList = this.queryQbListByYaQbId(courseId, "2");
        List<ExamQuestionBank> qbYnList = this.queryQbListByYaQbId(courseId, "3");

        QuestionBankCourseEnum qbEnum = QuestionBankCourseEnum.getEnumByCode(courseId);
        int singleSize = qbEnum.getSingle();
        int multipleSize = qbEnum.getMultiple();
        int yesNoSize = qbEnum.getYesNo();

        HashSet<Integer> sQuestionIndex = Sets.newHashSet();


        int maxNumberNo = qbSList.size() - 1;
        RandomUtil.randomSet(0, maxNumberNo, singleSize, sQuestionIndex);

        String rollId = Identities.uuid2();
        Date date = new Date();
        List<ExamRollUser> rollList = Lists.newArrayList();
        for (Integer i : sQuestionIndex) {
            ExamRollUser dto = new ExamRollUser();
            ExamQuestionBank examQuestionBank = qbSList.get(i);
            BeanUtils.copyProperties(examQuestionBank, dto);
            dto.setRollId(rollId);
            dto.setUserId(userId);
            dto.setCreatedBy(userId);
            dto.setUpdatedBy(userId);

            dto.setCreatedDate(date);
            dto.setUpdatedDate(date);
            rollList.add(dto);
            // TODO db rollSList
        }


        return rollId;
    }

    /**
     * 找指定题目
     *
     * @param courseId 科目：在押题库里过滤出相关题库id
     * @param type     题目类型：在相关题库里过滤
     * @return
     */
    private List<ExamQuestionBank> queryQbListByYaQbId(String courseId, String type) {
        List<ExamQuestionBank> rs = Lists.newArrayList();
        List<ExamQuestionBankYa> qbYaList = this.queryQbYa(courseId);
        if (CollectionUtils.isNotEmpty(qbYaList)) {
            for (ExamQuestionBankYa qbYa : qbYaList) {
                String questionBankId = qbYa.getQuestionBankId();
                List<ExamQuestionBank> qbList = this.queryQb(questionBankId, type);
                if (CollectionUtils.isNotEmpty(qbList)) {
                    rs.addAll(qbList);
                }
            }
            return rs;
        }
//        log.error("找不到指定题目,courseId:{}, type:{}", courseId, type);
        return null;
    }


    /**
     * 题库
     *
     * @param questionBankId
     * @return
     */
    private List<ExamQuestionBank> queryQb(String questionBankId,
                                           String type) {
        ExamQuestionBankExample example = new ExamQuestionBankExample();
        ExamQuestionBankExample.Criteria criteria = example.createCriteria();

        if (StringUtils.isNotBlank(questionBankId)) {
            criteria.andQuestionBankIdEqualTo(questionBankId);
        }
        if (StringUtils.isNotBlank(type)) {
            criteria.andTypeEqualTo(type);
        }
        List<ExamQuestionBank> list = examQuestionBankMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(list)) {
            return list;
        }
        return null;
    }

    /**
     * 押题库
     *
     * @param courseId
     * @return
     */
    private List<ExamQuestionBankYa> queryQbYa(String courseId) {
        ExamQuestionBankYaExample example = new ExamQuestionBankYaExample();
        example.createCriteria().andCourseIdEqualTo(courseId);
        List<ExamQuestionBankYa> list = examQuestionBankYaMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(list)) {
            return list;
        }
        return null;
    }


}
