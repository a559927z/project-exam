package com.ks.controller;

import com.ks.dto.ExamUserInfo;
import com.ks.dto.KVItemDto;
import com.ks.service.AppRollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @GetMapping
    @RequestMapping("/randomRoll")
    public KVItemDto<Boolean, String> randomRoll(String courseId) {
        KVItemDto<Boolean, String> rs = new KVItemDto<>();
        ExamUserInfo userInfo = getUserInfo();
        try {
            String rollId = appRollService.randomRoll(courseId, userInfo.getAccount());
            rs.setK(true);
            rs.setV(rollId);
        } catch (Exception e) {
            rs.setK(false);
            rs.setV(null);
        }
        return rs;
    }

}
