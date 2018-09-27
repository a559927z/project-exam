package com.ks.controller;

import com.ks.dao.ExamQuestionBankYaMapper;
import com.ks.dao.ExamUserAnswerYaMapper;
import com.ks.dto.KVItemDto;
import com.ks.dto.RegUserDto;
import net.chinahrd.utils.verifyCode.VerifyCodeUtils;
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
@RequestMapping("/app/reg")
@Controller
public class AppRegController {


    @Value("${ctx}")
    private String ctx;

    @Autowired
    private ExamQuestionBankYaMapper examQuestionBankYaMapper;
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
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        // 存入会话session
        HttpSession session = request.getSession(true);
        // 删除以前的
        session.removeAttribute("verCode");
        session.setAttribute("verCode", verifyCode.toLowerCase());

        // 生成图片
        int w = 162, h = 45;
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
    }


    @PostMapping
    @ResponseBody
    @RequestMapping("/regUser")
    public KVItemDto<Boolean, Object> regUser(RegUserDto regUserDto) {
        KVItemDto<Boolean, Object> rs = new KVItemDto<>();
        // check verificationCode

        // check exists user


        // reg success
        System.out.println(regUserDto);

        return rs;
    }

}
