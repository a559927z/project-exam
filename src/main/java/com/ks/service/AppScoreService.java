package com.ks.service;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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

    void calcScore(List<String> idList, String questionBankId, String enName);
}
