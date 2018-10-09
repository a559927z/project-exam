package com.ks.service;

import com.ks.dto.ExamUserAnswerYa;
import com.ks.vo.ExamUserAnswerYaVo;

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
public interface AppYaService {
    
    /**
     * 押题管理列表
     *
     * @param userId
     * @param courseId
     * @return
     */
    List<ExamUserAnswerYaVo> queryYaTiByUserId(String userId, String courseId);
}
