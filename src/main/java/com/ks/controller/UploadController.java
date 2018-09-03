package com.ks.controller;

import com.ks.dto.BaseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;

import org.apache.commons.io.IOUtils;

/**
 * Title: ${type_name} <br/>
 * <p>
 * Description: <br/>
 *
 * @author jxzhang
 * @DATE 2018年08月24日 17:06
 * @Verdion 1.0 版本
 * ${tags}
 */
@RequestMapping("/admin/upload")
@Controller
public class UploadController extends BaseController {

    /**
     * http://localhost:8080/exam/admin/upload/index
     *
     * @param locale
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(Locale locale, Model model) {
        return "admin/uploadAdmin";
    }


    /**
     * 解析xls
     *
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/parseXls2Dto", method = {RequestMethod.GET, RequestMethod.POST})
    public String parseXls2Dto(HttpServletRequest request, HttpServletResponse response) {
        try {
            @SuppressWarnings("unchecked")
            List<String> lines = IOUtils.readLines(request.getInputStream());
            StringBuilder sb = new StringBuilder();
            for (String str : lines) {
                sb.append(str);
            }
//            BaseDto dto = JSON.parse(sb.toString(), BaseDto.class);
//            System.out.println(com.alibaba.fastjson.JSON.toJSONString(dto));
            return "成功";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "失败";
    }
}

