package com.ks.config;

import com.ks.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Title: ${type_name} <br/>
 * <p>
 * Description: <br/>
 *
 * @author jxzhang
 * @DATE 2018年11月08日 15:37
 * @Verdion 1.0 版本
 * ${tags}
 */
@Component
public class Task {

    @Autowired
    private CommonService commonService;

    /**
     * 每隔12小时清空log表
     */
    @Scheduled(cron = "0 0 */12 * * ?")
    public void deleteLog() {
        commonService.deleteLog();
    }
}
