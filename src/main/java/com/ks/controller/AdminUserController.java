package com.ks.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.ks.constants.QuestionBankCategoryEnum;
import com.ks.constants.QuestionBankCourseEnum;
import com.ks.dao.PublicUserInfoMapper;
import com.ks.dto.*;
import com.ks.service.PublicPermissionService;
import net.chinahrd.utils.CollectionKit;
import net.didion.jwnl.data.Exc;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * Title: ${type_name} <br/>
 * <p>
 * Description: <br/>
 *
 * @author jxzhang
 * @DATE 2018年09月14日 00:48
 * @Verdion 1.0 版本
 * ${tags}
 */
@RequestMapping("/admin/user")
@Controller
public class AdminUserController {

    @Autowired
    private PublicPermissionService publicPermissionService;

    @Autowired
    private PublicUserInfoMapper publicUserInfoMapper;

    /**
     * http://localhost:8080/exam/admin/user/index
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "admin/userAdmin";
    }


    /**
     * http://localhost:8080/exam/admin/user/list
     *
     * @param request
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     */
    @ResponseBody
    @RequestMapping(value = "/list")
    public Map<String, Object> queryUserList(
            HttpServletRequest request,
            HttpServletResponse response) throws UnsupportedEncodingException {

        String draw = request.getParameter("draw");
        Integer startIndex = Integer.parseInt(request.getParameter("startIndex"));
        Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
        String search = request.getParameter("search");
        String questionBankId = request.getParameter("questionBankId");
        String orderColumn = request.getParameter("orderColumn");
        if (orderColumn == null) {
            orderColumn = "search";
        }


        PublicUserInfoExample example = new PublicUserInfoExample();
        example.createCriteria().andEnNameEqualTo(search);

        Page<PublicUserInfo> publicUserInfoList = publicPermissionService.queryByPage(startIndex, pageSize);


        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<PublicUserInfo> pageInfo = new PageInfo<>(publicUserInfoList);

        // 结果集
        List<PublicUserInfo> list = pageInfo.getList();
        // 总记录数
        long total = pageInfo.getTotal();
        // 每页的数量
        pageInfo.getPageSize();


        Map<String, Object> rsMap = CollectionKit.newMap();
        rsMap.put("data", list);
        rsMap.put("total", total);
        rsMap.put("draw", draw);
        return rsMap;
    }

    /**
     * <p>
     * http://localhost:8080/exam/admin/user/delete
     */
    @ResponseBody
    @RequestMapping(value = "/delete")
    public KVItemDto<Boolean, String> delete(@RequestParam("userIds[]") List<String> userIds) {
        KVItemDto<Boolean, String> rs = new KVItemDto<>();
        try {
            userIds.forEach(i -> {
                PublicUserInfoExample example = new PublicUserInfoExample();
                example.createCriteria().andEnNameEqualTo(i);
                publicUserInfoMapper.deleteByExample(example);
            });
            rs.setK(true);
            rs.setV("删除成功");
        } catch (Exception e) {
            rs.setK(false);
            rs.setV("删除失败");
        }
        return rs;
    }

    /**
     * <p>
     * http://localhost:8080/exam/admin/user/uploadStatus
     */
    @ResponseBody
    @RequestMapping(value = "/uploadStatus")
    public KVItemDto<Boolean, String> uploadStatus(@RequestParam("userIds[]") List<String> userIds) {
        KVItemDto<Boolean, String> rs = new KVItemDto<>();
        try {
            userIds.forEach(i -> {
                PublicUserInfoExample example = new PublicUserInfoExample();
                example.createCriteria().andEnNameEqualTo(i);
                PublicUserInfo publicUserInfo = publicUserInfoMapper.selectByExample(example).get(0);

                int state = publicUserInfo.getState();
                //取反
                state = state == 0 ? 1 : 0;

                PublicUserInfo value = new PublicUserInfo();
                value.setState(state);
                publicUserInfoMapper.updateByExampleSelective(value, example);
            });
            rs.setK(true);
            rs.setV("删除成功");
        } catch (Exception e) {
            rs.setK(false);
            rs.setV("删除失败");
        }
        return rs;
    }
}