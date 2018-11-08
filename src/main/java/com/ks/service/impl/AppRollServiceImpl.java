package com.ks.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.ks.constants.EisWebContext;
import com.ks.constants.QuestionBankCourseEnum;
import com.ks.dao.*;
import com.ks.dto.*;
import com.ks.service.AppRollService;
import com.ks.service.CommonService;
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
    private CommonService commonService;

    @Autowired
    private ExamRollUserMapper examRollUserMapper;

    @Autowired
    private ExamRollAnswerMapper examRollAnswerMapper;

    @Autowired
    private ExamQuestionBankMapper examQuestionBankMapper;

    @Autowired
    private ExamQuestionBankYaMapper examQuestionBankYaMapper;

    @Autowired
    private ExamQuestionBankAnswerMapper examQuestionBankAnswerMapper;

    private final String module = "随机组卷Service";

    @Override
    public String randomRoll(String courseId, String userId) {

        if (StringUtils.isBlank(courseId)) {
            return null;
        }

        List<ExamQuestionBank> qbSList = this.queryQbListByYaQbId(courseId, "1");
        List<ExamQuestionBank> qbMList = this.queryQbListByYaQbId(courseId, "2");
        List<ExamQuestionBank> qbYnList = this.queryQbListByYaQbId(courseId, "3");

        ExamUserInfo userInfo = EisWebContext.getUserInfo();
        String account = userInfo.getAccount();

        /**
         * 单选题不可能为空
         */
        if (null == qbSList) {
            commonService.saveLog("单选题不可能为空", module, account);
            return null;
        }

        QuestionBankCourseEnum qbEnum = QuestionBankCourseEnum.getEnumByCode(courseId);
        int singleSize = qbEnum.getSingle();
        int multipleSize = qbEnum.getMultiple();
        int yesNoSize = qbEnum.getYesNo();

        HashSet<Integer> sQuestionIndex = Sets.newHashSet();
        int maxNumberNo = qbSList.size() - 1;
        RandomUtil.randomSet(0, maxNumberNo, singleSize, sQuestionIndex);

        String rollId = Identities.uuid2();
        Date date = new Date();

        List<ExamRollUser> sRoll = this.groupRoll(qbSList, singleSize, account, date, rollId);
        saveRoll(sRoll);
        if (null != qbMList) {
            List<ExamRollUser> mRoll = this.groupRoll(qbMList, multipleSize, account, date, rollId);
            saveRoll(mRoll);
        }
        if (null != qbYnList) {
            List<ExamRollUser> ynRoll = this.groupRoll(qbYnList, yesNoSize, account, date, rollId);
            saveRoll(ynRoll);
        }
        return rollId;
    }

    /**
     * 入库
     *
     * @param rollList
     * @return
     */
    private void saveRoll(List<ExamRollUser> rollList) {
        rollList.forEach(i -> {
            // 卷
            examRollUserMapper.insert(i);
            String questionId = i.getQuestionId();
            List<ExamQuestionBankAnswer> qbAnswer = this.queryTrueAnswer(questionId);
            if (CollectionUtils.isNotEmpty(qbAnswer)) {
                qbAnswer.forEach(n -> {
                    ExamRollAnswer dto = new ExamRollAnswer();
                    BeanUtils.copyProperties(n, dto);
                    dto.setRollId(i.getRollId());
                    examRollAnswerMapper.insertSelective(dto);
                });
            }
        });
    }


    /**
     * 正确答案
     *
     * @param questionId
     * @return
     */
    private List<ExamQuestionBankAnswer> queryTrueAnswer(String questionId) {
        ExamQuestionBankAnswerExample qbAnswerExample = new ExamQuestionBankAnswerExample();
        qbAnswerExample.createCriteria()
                .andQuestionIdEqualTo(questionId)
                .andIsanswerEqualTo(true);
        List<ExamQuestionBankAnswer> trueAnswerList = examQuestionBankAnswerMapper.selectByExample(qbAnswerExample);
        if (CollectionUtils.isNotEmpty(trueAnswerList)) {
            return trueAnswerList;
        }
        commonService.saveLog("随机组卷时，找不到正确答案", module, "");
        return null;
    }


    /**
     * 组卷
     *
     * @param qbList      题库
     * @param size        题量
     * @param rollId      卷号ID
     * @param createdBy   创建人
     * @param createdDate 创建时间
     * @return
     */
    private List<ExamRollUser> groupRoll(List<ExamQuestionBank> qbList, int size, String createdBy, Date createdDate, String rollId) {
        // 随机生成题号
        HashSet<Integer> questionIndex = Sets.newHashSet();
        int maxNumberNo = qbList.size() - 1;
        RandomUtil.randomSet(0, maxNumberNo, size, questionIndex);

        // XXX bug:随机生成题，可以qbList里没有这么长。例如: random 23, qbList.size == 10
        List<ExamRollUser> rollList = Lists.newArrayList();
        for (Integer i : questionIndex) {
            ExamRollUser dto = new ExamRollUser();
            ExamQuestionBank examQuestionBank = qbList.get(i);
            BeanUtils.copyProperties(examQuestionBank, dto);
            dto.setRollId(rollId);
            dto.setUserId(createdBy);
            dto.setCreatedBy(createdBy);
            dto.setUpdatedBy(createdBy);

            dto.setCreatedDate(createdDate);
            dto.setUpdatedDate(createdDate);
            rollList.add(dto);
        }
        return rollList;
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
