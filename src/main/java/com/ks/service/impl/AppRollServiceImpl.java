package com.ks.service.impl;

import com.google.common.base.Splitter;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.ks.constants.EisWebContext;
import com.ks.constants.QuestionBankCourseEnum;
import com.ks.dao.*;
import com.ks.dto.*;
import com.ks.service.AppRollService;
import com.ks.service.CommonService;
import com.ks.utils.RandomUtil;
import com.ks.vo.AnswerVo;
import com.ks.vo.ScoreVo;
import lombok.extern.slf4j.Slf4j;
import net.chinahrd.utils.CollectionKit;
import net.chinahrd.utils.Identities;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    private ExamRollUserAnswerMapper examRollUserAnswerMapper;

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
            commonService.saveLog("单选题不可能为空", module + "/randomRoll", account);
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

    @Override
    public List<AnswerVo> getData(String rollId) {
        List<ExamRollUser> ruList = this.queryRoll(rollId);
        ExamRollAnswerExample raExample = new ExamRollAnswerExample();
        raExample.createCriteria().andRollIdEqualTo(rollId);
        List<ExamRollAnswer> rAnswerList = examRollAnswerMapper.selectByExample(raExample);
        List<AnswerVo> voList = Lists.newArrayList();
        ruList.forEach(i -> {
            String questionId = i.getQuestionId();
            AnswerVo vo = new AnswerVo();
            vo.setRollId(rollId);
            vo.setQuestionId(questionId);
            vo.setJieXi(i.getJieXi());
            vo.setType(i.getType());
            vo.setTitle(i.getTitle());
            List<String> answerList = Lists.newArrayList();
            List<String> trueAnswerList = Lists.newArrayList();
            rAnswerList.forEach(j -> {
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
        List<AnswerVo> rsList = Lists.newArrayList();
        for (int i = 0; i < voList.size(); i++) {
            AnswerVo vo = voList.get(i);
            vo.setNo(i + 1);
            rsList.add(vo);
        }
        return rsList;
    }

    @Override
    public List<ExamRollUserAnswer> queryUserAnswer(String rollId, String userId) {
        ExamRollUserAnswerExample ruaExample = new ExamRollUserAnswerExample();
        ruaExample.createCriteria()
                .andRollIdEqualTo(rollId)
                .andUserIdEqualTo(userId);

        return examRollUserAnswerMapper.selectByExample(ruaExample);
    }

    /**
     * 交卷入库
     *
     * @param idList
     * @param rollId
     * @param enName
     */
    @Override
    public void saveScore(List<String> idList, String rollId, String enName) {
        if (CollectionUtils.isEmpty(idList)) {
            commonService.saveLog("没有答题，交白卷,rollId" + rollId, module + "/saveScore", "");
            log.info("没有答题");
        }
        List<ExamRollUser> ruList = this.queryRoll(rollId);
        String courseId = ruList.get(0).getCourseId();

        List<ExamRollUserAnswer> userAnswerList = Lists.newArrayList();
        idList.forEach(i -> {
            ArrayList<String> strings = Lists.newArrayList(Splitter.on("@").omitEmptyStrings().trimResults().split(i));
            String selectAnswer = strings.get(0);
            String questionId = strings.get(1);

            ExamRollUserAnswer dto = new ExamRollUserAnswer();
            dto.setUserAnswerId(Identities.uuid2());
            dto.setUserId(enName);
            dto.setRollId(rollId);
            dto.setQuestionId(questionId);
            dto.setUserAnswer(selectAnswer);
            dto.setCourseId(courseId);
            ruList.forEach(j -> {
                if (questionId.equals(j.getQuestionId())) {
                    dto.setType(j.getType());
                }
            });
            userAnswerList.add(dto);
        });

        Multimap<String, ExamRollUserAnswer> groupByQuestionId = ArrayListMultimap.create();
        userAnswerList.forEach(i -> {
            String questionId = i.getQuestionId();
            groupByQuestionId.put(questionId, i);
        });

        // 正确答案
        List<ExamRollAnswer> trueAnswerList = this.queryTrueAnswer(rollId);
        Set<Map.Entry<String, Collection<ExamRollUserAnswer>>> userAnswerEntries = groupByQuestionId.asMap().entrySet();
        for (Map.Entry<String, Collection<ExamRollUserAnswer>> entry : userAnswerEntries) {
            String questionId = entry.getKey();
            String trueAnswerStr = "";
            for (ExamRollAnswer trueAnswer : trueAnswerList) {
                if (questionId.equals(trueAnswer.getQuestionId())) {
                    trueAnswerStr += trueAnswer.getAnswerno();
                }
            }
            List<ExamRollUserAnswer> tempUserAnswerList = Lists.newArrayList();
            tempUserAnswerList.addAll(entry.getValue());

            ExamRollUserAnswer readyDB = new ExamRollUserAnswer();
            BeanUtils.copyProperties(tempUserAnswerList.get(0), readyDB);

            boolean yes = false;
            String userAnswerStr = "";
            for (ExamRollUserAnswer uaAnswer : tempUserAnswerList) {
                String userAnswerNo = uaAnswer.getUserAnswer();
                userAnswerStr += userAnswerNo;
                // 将一方变字符串后，另一方循环检查是否包含
                if (StringUtils.containsAny(userAnswerNo, trueAnswerStr)) {
                    yes = true;
                } else {
                    // 只要有一个是错，本题剩下的用户答案都不用验了
                    yes = false;
                    break;
                }
            }
            readyDB.setTrueAnswer(trueAnswerStr);
            readyDB.setUserAnswer(userAnswerStr);
            if (yes) {
                readyDB.setIsYes(yes);
            } else {
                readyDB.setIsYes(false);
            }
            ExamRollUserAnswerExample delExample = new ExamRollUserAnswerExample();
            delExample.createCriteria().andQuestionIdEqualTo(readyDB.getQuestionId()).andUserIdEqualTo(readyDB.getUserId());
            examRollUserAnswerMapper.deleteByExample(delExample);
            examRollUserAnswerMapper.insertSelective(readyDB);
        }
    }

    @Override
    public List<ScoreVo> calcScore(String rollId, String userId) {
        ExamRollUserAnswerExample ruaExample = new ExamRollUserAnswerExample();
        ruaExample.createCriteria()
                .andRollIdEqualTo(rollId)
                .andUserIdEqualTo(userId);
        List<ExamRollUserAnswer> ruaList = examRollUserAnswerMapper.selectByExample(ruaExample);
        if (CollectionUtils.isEmpty(ruaList)) {
            log.info("没有答题");
        }
        return null;
    }

    /**
     * 查卷
     *
     * @param rollId
     * @return
     */
    private List<ExamRollUser> queryRoll(String rollId) {
        ExamRollUserExample ruExample = new ExamRollUserExample();
        ruExample.createCriteria().andRollIdEqualTo(rollId);
        ruExample.setOrderByClause("type asc");
        List<ExamRollUser> ruList = examRollUserMapper.selectByExample(ruExample);
        if (CollectionKit.isEmpty(ruList)) {
            commonService.saveLog("没找到卷,rollId" + rollId, module + "/getData", "");
            return null;
        }
        return ruList;
    }

    /**
     * 正确答案
     *
     * @param rollId
     * @return
     */
    private List<ExamRollAnswer> queryTrueAnswer(String rollId) {
        ExamRollAnswerExample example = new ExamRollAnswerExample();
        example.createCriteria()
                .andRollIdEqualTo(rollId)
                .andIsanswerEqualTo(true);
        List<ExamRollAnswer> trueAnswerList = examRollAnswerMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(trueAnswerList)) {
            return trueAnswerList;
        }
        commonService.saveLog("找不到正确答案", module + "/queryTrueAnswer", "");
        return null;
    }

    /**
     * 组卷入库
     *
     * @param rollList
     * @return
     */
    private void saveRoll(List<ExamRollUser> rollList) {
        rollList.forEach(i -> {
            // 卷
            examRollUserMapper.insert(i);
            String questionId = i.getQuestionId();
            List<ExamQuestionBankAnswer> qbAnswer = this.queryAnswer(questionId);
            if (CollectionUtils.isNotEmpty(qbAnswer)) {
                qbAnswer.forEach(n -> {
                    ExamRollAnswer dto = new ExamRollAnswer();
                    BeanUtils.copyProperties(n, dto);
                    dto.setId(Identities.uuid2());
                    dto.setRollId(i.getRollId());
                    examRollAnswerMapper.insert(dto);
                });
            }
        });
    }


    /**
     * 答案
     *
     * @param questionId
     * @return
     */
    private List<ExamQuestionBankAnswer> queryAnswer(String questionId) {
        ExamQuestionBankAnswerExample qbAnswerExample = new ExamQuestionBankAnswerExample();
        qbAnswerExample.createCriteria()
                .andQuestionIdEqualTo(questionId);
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
