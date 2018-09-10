package com.ks.controller;

import com.ks.dao.ExamQuestionBankMapper;
import com.ks.dto.ExamQuestionBank;
import com.ks.service.UploadService;
import groovy.util.logging.Slf4j;
import net.chinahrd.utils.CollectionKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import java.util.Map;


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
@Slf4j
@Controller
@RequestMapping("/admin/questionBank")
public class QuestionBankController extends BaseController {

    @Autowired
    private UploadService uploadService;


    @Autowired
    private ExamQuestionBankMapper examQuestionBankMapper;


    /**
     * http://localhost:8080/exam/admin/questionBank/index
     *
     * @param locale
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(Locale locale, Model model) {
        return "admin/questionBankAdmin";
    }

    /**
     * @param request
     */
    @ResponseBody
    @RequestMapping(value = "/list.do")
    public Map<String, Object> queryUserList(
            HttpServletRequest request,
            HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        //直接返回前台
        String draw = request.getParameter("draw");
        //数据起始位置
        Integer startIndex = Integer.parseInt(request.getParameter("startIndex"));
        //数据长度
        Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
        //原生搜索
        String search = request.getParameter("search");
        //获取客户端需要那一列排序
        String orderColumn = request.getParameter("orderColumn");
        if (orderColumn == null) {
            orderColumn = "user_key";
        }
        //获取排序方式 默认为asc
        String orderDir = request.getParameter("orderDir");
        if (orderDir == null) {
            orderDir = "asc";
        }


        List<ExamQuestionBank> examQuestionBanks = examQuestionBankMapper.selectByExample(null);


//        List<UserLockVo> userLockList = empMgrService.queryEmpList(startIndex, pageSize, search, orderColumn, orderDir);
        Map<String, Object> rsMap = CollectionKit.newMap();
        rsMap.put("data", examQuestionBanks);
        rsMap.put("total", examQuestionBanks.size());
        rsMap.put("draw", draw);

        return rsMap;
//        buildSuccessResponse(request, response, rsMap);
    }


}

