package com.ks.service;

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
}
