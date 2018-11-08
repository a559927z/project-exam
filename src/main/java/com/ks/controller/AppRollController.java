package com.ks.controller;

import com.ks.constants.EisWebContext;
import com.ks.dto.ExamUserInfo;
import com.ks.dto.KVItemDto;
import com.ks.service.AppRollService;
import com.ks.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Title: ${type_name} <br/>
 * <p>
 * Description: <br/>
 *
 * @author jxzhang
 * @DATE 2018年09月17日 22:53
 * @Verdion 1.0 版本
 * ${tags}
 */
@RequestMapping("/app/roll")
@Controller
public class AppRollController extends BaseController {

    @Autowired
    private AppRollService appRollService;

    @Autowired
    private CommonService commonService;

    private final String module = "随机组卷controller/randomRoll";

    /**
     * http://localhost:8080/exam/app/roll/toIndex
     *
     * @return
     */
    @RequestMapping("/toIndex")
    public String toRoll() {
        return "app/rollRandomRollApp";
    }


    /**
     * 随机组卷
     *
     * @param courseId 科目类别
     * @return
     */
    @ResponseBody
    @GetMapping
    @RequestMapping("/randomRoll")
    public KVItemDto<Boolean, String> randomRoll(String courseId) {
        KVItemDto<Boolean, String> rs = new KVItemDto<>();
        ExamUserInfo userInfo = EisWebContext.getUserInfo();
        rs.setK(false);
        rs.setV(null);
        try {
            String rollId = appRollService.randomRoll(courseId, userInfo.getAccount());
            if (null != rollId) {
                rs.setK(true);
                rs.setV(rollId);
            }
        } catch (Exception e) {
            commonService.saveLog("随机组卷失败，rollId为空", module, userInfo.getAccount());
            rs.setK(false);
            rs.setV(null);
        }
        return rs;
    }

}
