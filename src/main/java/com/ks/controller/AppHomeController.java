package com.ks.controller;

import com.alibaba.fastjson.JSON;
import com.ks.constants.CookieConstants;
import com.ks.constants.UrlConstants;
import com.ks.constants.UserInfoConstants;
import com.ks.dao.PublicUserInfoMapper;
import com.ks.dto.PublicUserInfo;
import com.ks.dto.PublicUserInfoExample;
import com.ks.utils.CookieUtils;
import com.ks.utils.cache.LoadingCacheUtil;
import com.ks.vo.VisitorVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/app/home")
@Controller
public class AppHomeController extends BaseController {

    @Autowired
    private PublicUserInfoMapper publicUserInfoMapper;


    /**
     * 重定向
     */
    private final String REDIRECT_TO_HOME = "redirect:toIndex";

    @RequestMapping("/")
    public String root() {
        return REDIRECT_TO_HOME;
    }


    /**
     * http://localhost:8080/exam/app/toHome
     *
     * @return
     */
    @RequestMapping("/toIndex")
    public String index(HttpServletRequest request) throws ExecutionException {
        String enName = getVisitor(request).getEnName();


        if (StringUtils.isBlank(enName)) {
            return UrlConstants.PAGE_TO_LOGIN;
        }
        LoadingCacheUtil loadingCacheUtil = LoadingCacheUtil.getInstance();
        if (null == loadingCacheUtil) {
            return UrlConstants.PAGE_TO_LOGIN;
        }
        String userInfo = loadingCacheUtil.get(enName, String.class);
        if (null == userInfo) {
            // 缓存没，再去数据检查一次，正常缓存是在/app/login/toLogin里就设置的，防止客户端2天没有退出，缓存没了。
            PublicUserInfoExample example = new PublicUserInfoExample();
            example.createCriteria().andEnNameEqualTo(enName).andStateEqualTo(UserInfoConstants.UN_LOCK);
            List<PublicUserInfo> exists = publicUserInfoMapper.selectByExample(example);
            if (CollectionUtils.isNotEmpty(exists)) {
                LoadingCacheUtil.getInstance().save(enName, JSON.toJSONString(exists.get(0)));
                return UrlConstants.PAGE_TO_HOME;
            } else {
                return UrlConstants.PAGE_TO_LOGIN;
            }
        }
        return UrlConstants.PAGE_TO_HOME;
    }


}
