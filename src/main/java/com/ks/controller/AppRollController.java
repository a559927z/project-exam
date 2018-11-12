package com.ks.controller;

import com.ks.constants.EisWebContext;
import com.ks.dao.ExamRollUserMapper;
import com.ks.dao.ExamRollUserMapperExt;
import com.ks.dto.ExamRollUser;
import com.ks.dto.ExamUserInfo;
import com.ks.dto.KVItemDto;
import com.ks.service.AppRollService;
import com.ks.service.CommonService;
import net.chinahrd.utils.CollectionKit;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
    private ExamRollUserMapperExt examRollUserMapperExt;

    @Autowired
    private AppRollService appRollService;

    @Autowired
    private CommonService commonService;

    private final String module = "随机组卷controller";

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
            e.printStackTrace();
            commonService.saveLog("随机组卷失败，rollId为空", module + "/randomRoll", userInfo.getAccount());
            rs.setK(false);
            rs.setV(null);
        }
        return rs;
    }

    /**
     * 开始答题
     *
     * @return
     */
    @GetMapping
    @RequestMapping("/toAnswer")
    public String toAnswer(String rollId, Model model) {
        model.addAttribute("rollId", rollId);
        return "app/rollAnswerApp";
    }

    /**
     * 组卷记录
     *
     * @return
     */
    @GetMapping
    @RequestMapping("/toRecord")
    public String toRecord() {
        return "app/rollRecordApp";
    }

    /**
     * 组卷记录列有
     *
     * @return
     */
    @ResponseBody
    @GetMapping
    @RequestMapping("/queryRecordByUserId")
    public KVItemDto<Boolean, List<ExamRollUser>> queryRecordByUserId() {
        KVItemDto<Boolean, List<ExamRollUser>> rs = new KVItemDto<>();
        ExamUserInfo userInfo = EisWebContext.getUserInfo();
        List<ExamRollUser> list = examRollUserMapperExt.queryRecordByUserId(userInfo.getAccount());

        if (CollectionUtils.isNotEmpty(list)) {
            rs.setK(true);
            rs.setV(list);
        } else {
            commonService.saveLog("没有组卷记录", module + "/queryRecordByUserId", userInfo.getAccount());
            rs.setK(false);
            rs.setV(null);
        }
        return rs;
    }
}
