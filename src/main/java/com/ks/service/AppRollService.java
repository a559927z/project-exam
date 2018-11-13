package com.ks.service;

import com.ks.dto.ExamRollUserAnswer;
import com.ks.dto.ScreenTiDto;
import com.ks.vo.AnswerVo;

import java.util.List;

/**
 * Title: ${type_name} <br/>
 * <p>
 * Description: <br/>
 *
 * @author jxzhang
 * @DATE 2018年11月07日 00:16
 * @Verdion 1.0 版本
 * ${tags}
 */
public interface AppRollService {

    /**
     * 随机组卷
     *
     * @param courseId 科目
     * @param userId   用户
     * @return
     */
    String randomRoll(String courseId, String userId);

    /**
     * 获取卷
     *
     * @param rollId
     * @return
     */
    List<AnswerVo> getData(String rollId);

    /**
     * 用户已答题答案
     *
     * @param rollId
     * @param userId
     * @return
     */
    List<ExamRollUserAnswer> queryUserAnswer(String rollId, String userId);

    /**
     * 入库 用户答案
     *
     * @param idList
     * @param roll
     * @param enName
     */
    void saveScore(List<String> idList, String roll, String enName);
}
