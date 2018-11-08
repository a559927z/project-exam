package com.ks.service;

/**
 * Title: ${type_name} <br/>
 * <p>
 * Description: <br/>
 *
 * @author jxzhang
 * @DATE 2018年08月27日 16:34
 * @Verdion 1.0 版本
 * ${tags}
 */
public interface CommonService {

    /**
     * 保存日志
     *
     * @param content 内容，内部转json对象存
     * @param module  模块
     * @param optId   操作者ID
     */
    void saveLog(Object content, String module, String optId);

    /**
     * 删除
     */
    void deleteLog();

}
