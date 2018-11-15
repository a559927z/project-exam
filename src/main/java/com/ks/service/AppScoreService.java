package com.ks.service;

import com.ks.vo.ScoreVo;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Title: ${type_name} <br/>
 * <p>
 * Description: <br/>
 *
 * @author jxzhang
 * @DATE 2018年10月12日 15:41
 * @Verdion 1.0 版本
 * ${tags}
 */
public interface AppScoreService {

    /**
     * 入库 用户答案
     *
     * @param idList
     * @param questionBankId
     * @return
     * @throws ExecutionException
     */
    void saveScore(List<String> idList, String questionBankId, String userId) throws Exception;

    /**
     * 计算成绩 - 卷
     *
     * @param rollId
     * @param userId
     * @return
     */
    List<ScoreVo> calcRollScore(String rollId, String userId);

    /**
     * 计算成绩 - 押题
     *
     * @param questionBankId
     * @param userId
     * @return
     */
    List<ScoreVo> calcYaScore(String questionBankId, String userId);
}
