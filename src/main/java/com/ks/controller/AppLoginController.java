package com.ks.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.ks.constants.CookieContext;
import com.ks.dao.ExamQuestionBankYaMapper;
import com.ks.dao.ExamUserAnswerYaMapper;
import com.ks.dao.PublicLoginMapper;
import com.ks.dao.PublicUserInfoMapper;
import com.ks.dto.*;
import com.ks.util.ShiroUtils;
import com.ks.utils.CookieUtils;
import lombok.extern.slf4j.Slf4j;
import net.chinahrd.utils.Identities;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

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
@RequestMapping("/app/login")
@Controller
public class AppLoginController {

    @Autowired
    private PublicUserInfoMapper publicUserInfoMapper;

    /**
     * 重定向
     */
    private final String REDIRECT_TO_LOGIN = "redirect:toLogin";

    private final String PAGE_TO_LOGIN = "app/loginApp";

    private final String PAGE_TO_HOME = "app/homeApp";

    @RequestMapping("/")
    public String root() {
        return REDIRECT_TO_LOGIN;
    }


    /**
     * http://localhost:8080/exam/app/login/toLogin
     *
     * @return
     */
    @RequestMapping("/toLogin")
    public String toLogin(HttpServletRequest request) {
        // cookie 已有验证通过的
        String enName = CookieUtils.getCookieValue(request, CookieContext.USER_INFO_KEY);
        PublicUserInfoExample example = new PublicUserInfoExample();
        // cookie 取enName
        if (StringUtils.isNotBlank(enName)) {
            // 检查用户是否已经注册过
            example.createCriteria().andEnNameEqualTo(enName);
            List<PublicUserInfo> exists = publicUserInfoMapper.selectByExample(example);
            if (CollectionUtils.isEmpty(exists)) {
                return PAGE_TO_LOGIN;
            } else {
                Integer state = exists.get(0).getState();
                if (state == 1) {
                    log.info("等待后台激活，请后再试。");
                    return PAGE_TO_LOGIN;
                } else {
                    log.info("已注册成功");
                    return PAGE_TO_HOME;
                }
            }
        }
        return PAGE_TO_LOGIN;
    }


    /**
     * 登录
     *
     * @param request
     * @param response
     * @param requestDto
     * @return
     */
    @PostMapping
    @ResponseBody
    @RequestMapping("/doLogin")
    @Transactional(rollbackFor = Exception.class)
    public KVItemDto<Boolean, Object> doLogin(HttpServletRequest request, HttpServletResponse response, PublicUserInfo requestDto) {
        KVItemDto<Boolean, Object> rs = new KVItemDto<>();
        try {
            PublicUserInfoExample example = new PublicUserInfoExample();
            String enName = requestDto.getEnName();
            // 检查用户是否已经注册并激活过
            example.createCriteria().andEnNameEqualTo(enName).andStateEqualTo(0);
            List<PublicUserInfo> exists = publicUserInfoMapper.selectByExample(example);
            rs = regUser(response, requestDto, rs, exists);
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            rs.setK(false);
            rs.setV("落地失败");
            return rs;
        }
    }

    /**
     * 用户落地
     *
     * @param response
     * @param requestDto
     * @param rs
     * @param exists
     * @return
     */
    private KVItemDto<Boolean, Object> regUser(HttpServletResponse response, PublicUserInfo requestDto, KVItemDto<Boolean, Object> rs, List<PublicUserInfo> exists) {
        // 如果没有就走注册流程
        if (CollectionUtils.isEmpty(exists)) {
            PublicUserInfo dto = new PublicUserInfo();
            BeanUtils.copyProperties(requestDto, dto);
            dto.setUid(Identities.uuid2());
            //  这里密码盐,直接用密码明文
            dto.setSalt(requestDto.getPassword());
            dto.setPassword(ShiroUtils.shiroMd5Hash(requestDto.getPassword(), requestDto.getPassword(), 2));
            // 1用户被锁定，待后台激动为0
            dto.setState(1);
            publicUserInfoMapper.insertSelective(dto);
            CookieUtils.addCookie(response, CookieContext.USER_INFO_KEY, dto.getEnName(), CookieContext.MAX_AGE);
            rs.setK(true);
            rs.setV("注册成功，等待后台激活。");
        } else {
            rs.setK(false);
            rs.setV("注册失败");
        }
        return rs;
    }


    /**
     * http://localhost:8080/exam/admin/ya/queryYaTi
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveLogin")
    public Map<String, Object> saveLogin(String userId, String courseId) {
        Map<String, Object> rsMap = Maps.newHashMap();
        return rsMap;
    }


}
