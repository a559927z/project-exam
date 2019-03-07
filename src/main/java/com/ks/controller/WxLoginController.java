package com.ks.controller;

import com.alibaba.fastjson.JSON;
import com.ks.util.WXUtils;
import com.ks.vo.WxLoginVo;
import com.ks.vo.WxUserInfoVo;
import lombok.extern.slf4j.Slf4j;
import net.chinahrd.utils.HttpClientUtil;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
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
@RequestMapping("/wx/login")
@Controller
public class WxLoginController extends BaseController {


    private String appId = "wx75e508d9c524a12d";
    private String secret = "cec8da0e2174c27dab4ea96d66a5d6a7";

    @ResponseBody
    @PostMapping
    @RequestMapping("/login")
    public Map<String, Object> wxLogin(@RequestBody WxLoginVo vo) {
        String jsCode = vo.getCode();
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + "&secret=" + secret + "&js_code=" + jsCode + "&grant_type=authorization_code";
        String data = HttpClientUtil.get(url);
        System.out.println("请求结果：" + data);
        String openId = new JSONObject(data).getString("openid");
        System.out.println("获得openId: " + openId);

        WxLoginVo wxLoginVo = JSON.parseObject(data, WxLoginVo.class);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("data", wxLoginVo);
        return result;
    }


    @ResponseBody
    @PostMapping
    @RequestMapping("/findUserInfo")
    public Map<String, Object> wxUserInfo(@RequestBody WxUserInfoVo vo) {
        com.alibaba.fastjson.JSONObject userInfo = WXUtils.getUserInfo(vo.getEncryptedData(), vo.getSessionKey(), vo.getIv());
        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("data", userInfo);
        return result;
    }


}
