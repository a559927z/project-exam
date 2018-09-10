package com.ks.controller;

import com.google.common.collect.Lists;
import com.ks.dto.ExamQuestionBank;
import com.ks.service.UploadService;
import com.ks.utils.StringUtil;
import groovy.util.logging.Slf4j;
import net.chinahrd.utils.Identities;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;


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
@RequestMapping("/admin/upload")
public class UploadController extends BaseController {

    @Autowired
    private UploadService uploadService;

    private String TITLE_PATTERN = "^\\d+\\.\\S+";
    private String ANSWER_PATTERN = "☐";
    private String ANSWER_TRUE_PATTERN = "☑";
    private String JIE_XI_PATTERN = "解析:";
    private String NOTE_PATTERN = "押题说明:";

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
     * http://localhost:8080/exam/admin/upload/parseXls2Dto
     *
     * @param request
     * @param response
     * @return
     */
//    @ResponseBody
    @RequestMapping(value = "/parseXls2Dto", method = {RequestMethod.GET, RequestMethod.POST})
    public String parseXls2Dto(Model model,
                               MultipartFile file,
                               HttpServletRequest request, HttpServletResponse response) throws IOException, InvalidFormatException {
        String questionBankName = request.getParameter("questionBankName");
        String categoryId = request.getParameter("categoryVal");
        String courseId = request.getParameter("courseVal");
        String optionsRadiosInline = request.getParameter("optionsRadiosInline");


        if (null == file) {
            throw new RuntimeException("为空");
        }

        // jar包的路径
        String filePath = "";
        if (!file.isEmpty()) {
            File temp = new File("");
            filePath = temp.getAbsolutePath() + "\\" + file.getOriginalFilename();
            BufferedOutputStream out;
            try {
                out = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
                out.write(file.getBytes());
                out.flush();
                out.close();
                System.out.println("上传成功");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("上传失败");
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("上传失败");
            }
        }
        List<ExamQuestionBank> list = Lists.newArrayList();
        String title = "";
        String answer = "";
        String jieXi = "";
        String note = "";
        Integer flag = 0;

        Workbook sheets = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = sheets.getSheetAt(0);
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            Cell cell = row.getCell(0);

            String stringCellValue = cell.getStringCellValue()
                    .replaceAll(" ", "")
                    .replaceAll("   ", "")
                    .trim();

            boolean matches = Pattern.matches(TITLE_PATTERN, stringCellValue);
            if (matches) {
                title += stringCellValue;
                String nextVal = getNextVal(sheet, i);
                if (!(StringUtils.containsAny(nextVal, ANSWER_TRUE_PATTERN)
                        || StringUtils.containsAny(nextVal, ANSWER_PATTERN))) {
                    flag = 0;
                }
                continue;
            } else if (StringUtils.containsAny(stringCellValue, ANSWER_TRUE_PATTERN)
                    || StringUtils.containsAny(stringCellValue, ANSWER_PATTERN)) {

                answer += stringCellValue;
                String nextVal = getNextVal(sheet, i);
                if (!(StringUtils.startsWith(nextVal, JIE_XI_PATTERN))) {
                    flag = 1;
                }
                continue;
            } else if (StringUtils.startsWith(stringCellValue, JIE_XI_PATTERN)) {
                jieXi += stringCellValue;
                String nextVal = getNextVal(sheet, i);
                if (!(StringUtils.startsWith(nextVal, NOTE_PATTERN))) {
                    flag = 2;
                }
                continue;
            } else if (StringUtils.startsWith(stringCellValue, NOTE_PATTERN)) {
                note += stringCellValue;
                Row nextRow = sheet.getRow(i + 1);
                if (nextRow == null) {
                    ExamQuestionBank dto = new ExamQuestionBank();
                    dto.setQuestionBankId(Identities.uuid2());
                    dto.setQuestionBankName(questionBankName);
                    dto.setTitle(prossTitle(title));
                    dto.setAnswer(answer);
                    dto.setTrueAnswer(trueAnswer(answer));
                    dto.setJieXi(jieXi);
                    dto.setNote(note);
                    dto.setCategoryId(categoryId);
                    dto.setCourseId(courseId);
                    dto.setType(optionsRadiosInline);
                    dto.setIsLock(0);
                    list.add(dto);
                    break;
                }
                String nextVal = nextRow.getCell(0).getStringCellValue()
                        .replaceAll(" ", "")
                        .replaceAll("   ", "")
                        .trim();
                boolean endTitle = Pattern.matches(TITLE_PATTERN, nextVal);
                if (!endTitle) {
                    flag = 3;
                } else {
                    ExamQuestionBank dto = new ExamQuestionBank();
                    dto.setQuestionBankId(Identities.uuid2());
                    dto.setQuestionBankName(questionBankName);
                    dto.setTitle(prossTitle(title));
                    dto.setAnswer(answer);
                    dto.setTrueAnswer(trueAnswer(answer));
                    dto.setJieXi(jieXi);
                    dto.setNote(note);
                    dto.setCategoryId(categoryId);
                    dto.setCourseId(courseId);
                    dto.setType(optionsRadiosInline);
                    dto.setIsLock(0);
                    list.add(dto);
                    title = "";
                    answer = "";
                    jieXi = "";
                    note = "";
                }
                continue;
            }
            if (flag == 0) {
                title += stringCellValue;
            } else if (flag == 1) {
                answer += stringCellValue;
            } else if (flag == 2) {
                jieXi += stringCellValue;
            } else if (flag == 3) {
                note += stringCellValue;
            }
        }

        int successTotal = putStorage(list);
        model.addAttribute("successTotal" + successTotal);
        return "redirect:index";
    }

    /**
     * 入库
     *
     * @param list
     * @return 入库条数
     */
    private int putStorage(List<ExamQuestionBank> list) {
        int rs = 0;
        for (ExamQuestionBank dto : list) {
            rs += uploadService.insertSelective(dto);
        }
        return rs;
    }

    /**
     * 下一个cell的值
     *
     * @param sheet
     * @param i
     * @return
     */
    private String getNextVal(Sheet sheet, int i) {


        Row nextRow = sheet.getRow(i + 1);
        if (nextRow.getCell(0) != null) {
            // 把纯数字作为String类型读进来了
            nextRow.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
        }
        return nextRow.getCell(0).getStringCellValue().replaceAll(" ", "")
                .replaceAll("   ", "")
                .trim();
    }


    /**
     * 正确答案
     *
     * @param stringCellValue
     * @return
     */
    private String trueAnswer(String stringCellValue) {
        if (StringUtil.containsAny(stringCellValue, ANSWER_PATTERN)) {
//            System.out.println("答案=====>" + stringCellValue);
            if (StringUtil.containsAny(stringCellValue, ANSWER_TRUE_PATTERN)) {
                String ss = stringCellValue
                        .replaceAll(" ", "")
                        .replaceAll("   ", "");
                ss = StringUtil.substringAfter(ss, ANSWER_TRUE_PATTERN);
                ss = StringUtil.substringBefore(ss, ANSWER_PATTERN);
                ss = StringUtil.deleteWhitespace(ss).trim();
//                System.out.println("正确答案=====>" + ss);
                return ANSWER_TRUE_PATTERN + ss;
            }
        }
        return "";
    }


    public static void main(String[] args) {
//        String ss = "☐I、II   ☑I、II、III   ☐II、III   ☐I、III "
//                .replaceAll(" ", "")
//                .replaceAll("   ", "");
//
//        ss = StringUtil.substringAfter(ss, "☑");
//        ss = StringUtil.substringBefore(ss, "☐");
//        ss = StringUtil.deleteWhitespace(ss).trim();
//
//        System.out.println(ss);

//        String stringCellValue = "563.xxxx";
//        boolean matches = Pattern.matches("^\\d+\\.\\S+", stringCellValue);
//        System.out.println(matches);

        String stringCellValue = "5.63.xx.xx";
        int i = stringCellValue.indexOf(".");
        stringCellValue = stringCellValue.substring(i + 1, stringCellValue.length());
        System.out.println(stringCellValue);
    }

    private String prossTitle(String title) {
        int i = title.indexOf(".");
        return title.substring(i + 1, title.length());
    }
}


