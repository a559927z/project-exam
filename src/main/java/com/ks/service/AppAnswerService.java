package com.ks.service;

import com.ks.dto.ExamUserAnswerYa;
import com.ks.dto.ScreenTiDto;
import com.ks.vo.AnswerVo;

import java.util.List;

/**
 * Title: ${type_name} <br/>
 * <p>
 * Description: <br/>
 *
 * @author jxzhang
 * @DATE 2018年10月10日 10:32
 * @Verdion 1.0 版本
 * ${tags}
 */
public interface AppAnswerService {

    /**
     * @param screenTiDto
     * @return
     */
    List<AnswerVo> getData(ScreenTiDto screenTiDto);

    /**
     * 获取用户答案
     *
     * @param questionBankId
     * @param userId
     * @return
     */
    List<ExamUserAnswerYa> queryUserAnswer(String questionBankId, String userId);
}
