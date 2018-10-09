package com.ks.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.ks.constants.QuestionBankCategoryEnum;
import com.ks.constants.QuestionBankConstants;
import com.ks.constants.QuestionBankCourseEnum;
import com.ks.dao.ExamQuestionBankScoreMapper;
import com.ks.dto.*;
import com.ks.service.ExamQuestionBankService;
import groovy.util.logging.Slf4j;
import net.chinahrd.utils.CollectionKit;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
public class AdminQuestionBankController extends BaseController {

    @Autowired
    private ExamQuestionBankService examQuestionBankService;

    @Autowired
    private ExamQuestionBankScoreMapper examQuestionBankScoreMapper;

    /**
     * 查题库类型分数
     * <p>
     * http://localhost:8080/exam/admin/questionBank/updateQuestionType
     *
     * @return
     */
    @ResponseBody
    @GetMapping
    @RequestMapping(value = "/findScore")
    public Map<String, Object> findScore(String questionBankId) {

        ExamQuestionBankScoreExample example = new ExamQuestionBankScoreExample();
        example.createCriteria().andQuestionBankIdEqualTo(questionBankId);

        List<ExamQuestionBankScore> rs = examQuestionBankScoreMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(rs)) {
            return null;
        }
        Map<String, Object> rsMap = CollectionKit.newMap();
        rs.forEach(n -> {
            if (n.getQuestionBankType().equals("1")) {
                rsMap.put("singleId", n.getScore());
            } else if (n.getQuestionBankType().equals("2")) {
                rsMap.put("multipleId", n.getScore());
            } else if (n.getQuestionBankType().equals("3")) {
                rsMap.put("yesNoId", n.getScore());
            }
        });
        return rsMap;
    }

    /**
     * 设置分数
     * <p>
     * http://localhost:8080/exam/admin/questionBank/updateQuestionType
     *
     * @return
     */
    @ResponseBody
    @GetMapping
    @RequestMapping(value = "/updateQuestionType")
    public Map<String, Object> updateQuestionType(
            String questionBankId, Double singleId, Double multipleId, Double yesNoId) {

        int i = examQuestionBankService.updateQuestionType(questionBankId, singleId, multipleId, yesNoId);

        Map<String, Object> rsMap = CollectionKit.newMap();
        rsMap.put("k", i == 3 ? true : false);
        rsMap.put("v", i == 3 ? "成功" : "失败");
        return rsMap;
    }

    /**
     * 查出题库总数
     * <p>
     * http://localhost:8080/exam/admin/questionBank/queryTotal
     *
     * @param request
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     */
    @ResponseBody
    @RequestMapping(value = "/queryTotal")
    public Map<String, Object> queryTotal(
            HttpServletRequest request,
            HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        List<ExamQuestionBankTotal> examQuestionBankTotalList = examQuestionBankService.queryTotal();

        Map<String, Object> rsMap = CollectionKit.newMap();
        rsMap.put("data", examQuestionBankTotalList);
        rsMap.put("total", examQuestionBankTotalList.size());
        return rsMap;
    }

    /**
     * 删除题库
     * <p>
     * http://localhost:8080/exam/admin/questionBank/deleteQuestionBank
     *
     * @param questionBankId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteQuestionBank")
    public Map<String, Object> deleteQuestionBank(String questionBankId) {
        int i = examQuestionBankService.deleteByExample(questionBankId);
        Map<String, Object> rsMap = CollectionKit.newMap();

        if (i == QuestionBankConstants.USER_ANSWER_YA_USEING) {
            rsMap.put("success", false);
            rsMap.put("msg", "学员在“考前押题里”有操作记录，不能删除题库");
            return rsMap;
        } else if (i == QuestionBankConstants.QUESTION_BANK_YA_USEING) {
            rsMap.put("success", false);
            rsMap.put("msg", "已经被列入押题管理，请先再押题库里移除题库");
            return rsMap;
        } else {
            rsMap.put("success", true);
            rsMap.put("msg", "成功删除除");
            return rsMap;
        }
    }


    /**
     * 题库首页
     * <p>
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
     * 题库列表
     * <p>
     * http://localhost:8080/exam/admin/questionBank/list
     *
     * @param request
     */
    @ResponseBody
    @RequestMapping(value = "/list")
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
        // 自定参数
        String questionBankId = request.getParameter("questionBankId");
        //获取客户端需要那一列排序
        String orderColumn = request.getParameter("orderColumn");
        if (orderColumn == null) {
            orderColumn = "search";
        }
        //获取排序方式 默认为asc
        String orderDir = request.getParameter("orderDir");
        if (orderDir == null) {
            orderDir = "asc";
        }

        ExamQuestionBankExample example = new ExamQuestionBankExample();
        example.createCriteria().andQuestionBankNameEqualTo(search);

        Page<ExamQuestionBank> examQuestionBankList =
                examQuestionBankService.findByPage(startIndex, pageSize, questionBankId);
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<ExamQuestionBank> pageInfo = new PageInfo<>(examQuestionBankList);

        // 结果集
        List<ExamQuestionBank> list = pageInfo.getList();
        // 总记录数
        long total = pageInfo.getTotal();
        // 每页的数量
        pageInfo.getPageSize();

        List<ExamQuestionBankDto> rs = Lists.newArrayList();
        list.forEach((ExamQuestionBank n) -> {
            ExamQuestionBankDto dto = new ExamQuestionBankDto();
            BeanUtils.copyProperties(n, dto);
            dto.setCategoryId(QuestionBankCategoryEnum.getNameByCode(n.getCategoryId()));
            dto.setCourseId(QuestionBankCourseEnum.getNameByCode(n.getCourseId()));
            rs.add(dto);
        });

        Map<String, Object> rsMap = CollectionKit.newMap();
        rsMap.put("data", rs);
        rsMap.put("total", total);
        rsMap.put("draw", draw);
        return rsMap;
    }


}


