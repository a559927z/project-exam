package com.ks.controller;

import com.alibaba.fastjson.JSON;
import com.ks.constants.CookieConstants;
import com.ks.constants.UserInfoConstants;
import com.ks.dao.PublicUserInfoMapper;
import com.ks.dto.KVItemDto;
import com.ks.dto.PublicUserInfo;
import com.ks.dto.PublicUserInfoExample;
import com.ks.util.ShiroUtils;
import com.ks.utils.CookieUtils;
import com.ks.utils.StringUtil;
import com.ks.utils.cache.LoadingCacheUtil;
import com.ks.vo.VisitorVo;
import lombok.extern.slf4j.Slf4j;
import net.chinahrd.utils.Identities;
import net.chinahrd.utils.crypto.CryptUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.concurrent.ExecutionException;

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
public class AppLoginController extends BaseController {

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
     * 清空不是本版本其它cookie值
     *
     * @param request
     * @param response
     */
    private void checkVersion(HttpServletRequest request, HttpServletResponse response) {
        String version = CookieUtils.getCookieValue(request, CookieConstants.VERSION);
        if (StringUtils.isBlank(version)) {
            CookieUtils.deleteCookie(response, CookieConstants.USER_INFO_KEY);
            CookieUtils.addCookie(response, CookieConstants.VERSION, CookieConstants.VERSION, CookieConstants.MAX_AGE);
        }
    }

    /**
     * 登录
     * http://localhost:8080/exam/app/login/toLogin
     *
     * @return
     */
    @RequestMapping("/toLogin")
    public String toLogin(HttpServletRequest request, HttpServletResponse response) throws ExecutionException {
        checkVersion(request, response);
        VisitorVo visitor = getVisitor(request);
        String enName = "";
        if (null != visitor) {
            enName = visitor.getEnName();
        }
        // cookie 已有验证通过的
        PublicUserInfoExample example = new PublicUserInfoExample();
        // cookie 取enName
        if (StringUtils.isNotBlank(enName)) {
            // 检查用户是否已经注册过
            example.createCriteria()
                    .andEnNameEqualTo(enName)
                    .andSaltEqualTo(visitor.getPassword())
                    .andPasswordEqualTo(ShiroUtils.shiroMd5Hash(visitor.getPassword(), visitor.getPassword(), 2));
            List<PublicUserInfo> exists = publicUserInfoMapper.selectByExample(example);
            if (CollectionUtils.isEmpty(exists)) {
                return PAGE_TO_LOGIN;
            } else {
                PublicUserInfo publicUserInfo = exists.get(0);
                Integer state = publicUserInfo.getState();
                if (state == UserInfoConstants.IS_LOCK) {
                    log.info("等待后台激活，请后再试。");
                    return PAGE_TO_LOGIN;
                } else {
                    log.info("已注册成功");
                    LoadingCacheUtil.getInstance().save(enName, JSON.toJSONString(publicUserInfo));
                    return PAGE_TO_HOME;
                }
            }
        }
        return PAGE_TO_LOGIN;
    }


    /**
     * 登录注册
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
            example.createCriteria().andEnNameEqualTo(enName);
            List<PublicUserInfo> exists = publicUserInfoMapper.selectByExample(example);
            rs = saveLogin(response, requestDto, rs, exists);
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
    private KVItemDto<Boolean, Object> saveLogin(HttpServletResponse response, PublicUserInfo requestDto, KVItemDto<Boolean, Object> rs, List<PublicUserInfo> exists) {
        VisitorVo vo = new VisitorVo();
        vo.setEnName(requestDto.getEnName());
        vo.setPassword(requestDto.getPassword());
        String voJSON = JSON.toJSONString(vo);
        // 如果没有就走注册流程
        if (CollectionUtils.isEmpty(exists)) {
            PublicUserInfo dto = new PublicUserInfo();
            BeanUtils.copyProperties(requestDto, dto);
            dto.setUid(Identities.uuid2());
            //  这里密码盐,直接用密码明文
            dto.setSalt(requestDto.getPassword());
            dto.setPassword(ShiroUtils.shiroMd5Hash(requestDto.getPassword(), requestDto.getPassword(), 2));
            // 1用户被锁定，待后台激动为0
            dto.setState(UserInfoConstants.IS_LOCK);
            publicUserInfoMapper.insertSelective(dto);
            CookieUtils.addCookie(response, CookieConstants.USER_INFO_KEY, voJSON, CookieConstants.MAX_AGE);
            rs.setK(true);
            rs.setV("注册成功，等待后台激活。");
        } else if (exists.get(0).getState() == UserInfoConstants.IS_LOCK) {
            rs.setK(true);
            rs.setV("已注册过，等待后台激活。");
        } else if (exists.get(0).getState() == UserInfoConstants.UN_LOCK) {
            rs.setK(true);
            rs.setV("已注册过，已激活过。只是客户端清空了cookie");
            CookieUtils.addCookie(response, CookieConstants.USER_INFO_KEY, voJSON, CookieConstants.MAX_AGE);
        } else {
            rs.setK(false);
            rs.setV("注册失败");
        }
        return rs;
    }


    /**
     * 退出
     *
     * @param request
     * @return
     * @throws ExecutionException
     */
    @GetMapping
    @ResponseBody
    @RequestMapping("/toLogout")
    public Boolean toLogout(HttpServletRequest request, HttpServletResponse response) throws ExecutionException {
        String enName = getVisitor(request).getEnName();
        if (StringUtils.isBlank(enName)) {
            return false;
        }
        LoadingCacheUtil loadingCacheUtil = LoadingCacheUtil.getInstance();
        if (null == loadingCacheUtil) {
            return false;
        }
        loadingCacheUtil.delete(enName);
        CookieUtils.deleteCookie(response, CookieConstants.USER_INFO_KEY);
        return true;
    }

}
