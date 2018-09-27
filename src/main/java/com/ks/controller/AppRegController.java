package com.ks.controller;

import com.ks.dao.ExamQuestionBankYaMapper;
import com.ks.dao.ExamUserAnswerYaMapper;
import com.ks.dao.PublicUserInfoMapper;
import com.ks.dto.*;
import com.ks.util.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import net.chinahrd.utils.Identities;
import net.chinahrd.utils.verifyCode.VerifyCodeUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
@Slf4j
@RequestMapping("/app/reg")
@Controller
public class AppRegController {


    @Autowired
    private PublicUserInfoMapper publicUserInfoMapper;
    @Autowired
    private ExamUserAnswerYaMapper examUserAnswerYaMapper;


    /**
     * http://localhost:8080/exam/app/my/toIndex
     *
     * @return
     */
    @RequestMapping("/toIndex")
    public String toYaTi(HttpServletRequest request, Model model) throws IOException {
        return "app/regApp";
    }

    /**
     * 生成验证码图片
     *
     * @return
     */
    @GetMapping
    @RequestMapping("/authImage")
    public void authImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        // 生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4).toLowerCase();
        // 存入会话session
        HttpSession session = request.getSession(true);
        // 删除以前的
        session.removeAttribute("verCode_" + verifyCode);
        session.setAttribute("verCode_" + verifyCode, verifyCode);

        // 生成图片
        int w = 162, h = 45;
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
    }


    @PostMapping
    @ResponseBody
    @RequestMapping("/regUser")
    public KVItemDto<Boolean, Object> regUser(HttpServletRequest request, RegUserDto regUserDto) {
        KVItemDto<Boolean, Object> rs = new KVItemDto<>();
        // check verificationCode
        String verificationCode = regUserDto.getVerificationCode().toLowerCase();
        HttpSession session = request.getSession(true);
        Object sessionVerCode = session.getAttribute("verCode_" + verificationCode);
        if (null == sessionVerCode) {
            log.error("验证码不正确");
            return null;
        }
        // check exists user
        String phone = regUserDto.getPhone();
        PublicUserInfoExample example = new PublicUserInfoExample();
        example.createCriteria().andEnNameEqualTo(phone);
        List<PublicUserInfo> exists = publicUserInfoMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(exists)) {
            log.error("账号已经存在");
            return null;
        }

        String pwd = regUserDto.getPwd();
        String salt = Identities.uuid3();
        PublicUserInfo dto = new PublicUserInfo();
        dto.setUid(Identities.uuid2());
        dto.setEnName(phone);
        dto.setPassword(ShiroUtils.shiroMd5Hash(pwd, salt, 2));
        dto.setSalt(salt);
        dto.setState(1);
        try {
            // reg success
            publicUserInfoMapper.insertSelective(dto);
            rs.setK(true);
            rs.setV("绑定成功");
            return rs;
        } catch (Exception e) {
            rs.setK(false);
            rs.setV("绑定失败");
            return rs;
        }


    }

}
