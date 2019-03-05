package com.ks.controller;

import com.alibaba.fastjson.JSON;
import com.ks.constants.CookieConstants;
import com.ks.constants.UserInfoConstants;
import com.ks.dao.ExamUserInfoMapper;
import com.ks.dao.PublicUserInfoMapper;
import com.ks.dto.*;
import com.ks.util.ShiroUtils;
import com.ks.utils.CookieUtils;
import com.ks.utils.cache.LoadingCacheUtil;
import com.ks.vo.VisitorVo;
import com.ks.vo.WxLoginVo;
import lombok.extern.slf4j.Slf4j;
import net.chinahrd.utils.HttpClientUtil;
import net.chinahrd.utils.Identities;
import net.chinahrd.utils.RemoteUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@RequestMapping("/wx/login")
@Controller
public class WxLoginController extends BaseController {


    private String appId = "wx75e508d9c524a12d";
    private String secret = "cec8da0e2174c27dab4ea96d66a5d6a7";

    @PostMapping
    @RequestMapping("/login")
    public Map<String, Object>  wxLogin(@RequestBody WxLoginVo vo) {
        String jsCode = vo.getCode();
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + "&secret=" + secret + "&js_code=" + jsCode + "&grant_type=authorization_code";
        String data = HttpClientUtil.get(url);
        System.out.println("请求结果：" + data);
        String openId = new JSONObject(data).getString("openid");
        System.out.println("获得openId: " + openId);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("data", data);

        return result;
    }

}
