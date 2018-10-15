package com.ks.controller;

import com.google.common.collect.Lists;
import com.ks.constants.QuestionBankConstants;
import com.ks.constants.QuestionBankTypeEnum;
import com.ks.dao.ExamQuestionBankMapper;
import com.ks.dto.ExamQuestionBank;
import com.ks.dto.ExamQuestionBankExample;
import com.ks.service.UploadService;
import groovy.util.logging.Slf4j;
import net.chinahrd.utils.Identities;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
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
public class AdminUploadController extends BaseController {

    @Autowired
    private UploadService uploadService;

    @Autowired
    private ExamQuestionBankMapper examQuestionBankMapper;

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
    @RequestMapping(value = "/parseXls2Dto", method = {RequestMethod.GET, RequestMethod.POST})
    public String parseXls2Dto(Model model,
                               MultipartFile file,
                               HttpServletRequest request, HttpServletResponse response)
            throws IOException, InvalidFormatException {

        String questionBankName = request.getParameter("questionBankName");
        String categoryId = request.getParameter("categoryVal");
        String courseId = request.getParameter("courseVal");
        String questionBankId = Identities.uuid2();

        if (StringUtils.isBlank(questionBankName)) {
            throw new RuntimeException("题库名称为空");
        }

        if (null == file) {
            throw new RuntimeException("上传附件为空");
        }

        // 合并题库
        ExamQuestionBankExample example = new ExamQuestionBankExample();
        example.createCriteria().andQuestionBankNameEqualTo(questionBankName);
        List<ExamQuestionBank> examQuestionBankList = examQuestionBankMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(examQuestionBankList)) {
            questionBankId = examQuestionBankList.get(0).getQuestionBankId();
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

            String stringCellValue = trim(cell.getStringCellValue());

            boolean matches = Pattern.matches(QuestionBankConstants.TITLE_PATTERN, stringCellValue);
            if (matches) {
                title += stringCellValue;
                String nextVal = getNextVal(sheet, i);
                if (!(StringUtils.containsAny(nextVal, QuestionBankConstants.ANSWER_TRUE_PATTERN)
                        || StringUtils.containsAny(nextVal, QuestionBankConstants.ANSWER_PATTERN))) {
                    flag = 0;
                }
                continue;
            } else if (StringUtils.containsAny(stringCellValue, QuestionBankConstants.ANSWER_TRUE_PATTERN)
                    || StringUtils.containsAny(stringCellValue, QuestionBankConstants.ANSWER_PATTERN)) {

                answer += stringCellValue;
                String nextVal = getNextVal(sheet, i);
                if (!(StringUtils.startsWith(nextVal, QuestionBankConstants.JIE_XI_PATTERN))) {
                    flag = 1;
                }
                continue;
            } else if (StringUtils.startsWith(stringCellValue, QuestionBankConstants.JIE_XI_PATTERN)) {
                jieXi += stringCellValue;
                String nextVal = getNextVal(sheet, i);
                if (!(StringUtils.startsWith(nextVal, QuestionBankConstants.NOTE_PATTERN))) {
                    flag = 2;
                }
                continue;
            } else if (StringUtils.startsWith(stringCellValue, QuestionBankConstants.NOTE_PATTERN)) {
                note += stringCellValue;
                Row nextRow = sheet.getRow(i + 1);
                if (nextRow == null) {
                    ExamQuestionBank dto = new ExamQuestionBank();
                    dto.setQuestionId(Identities.uuid2());
                    dto.setQuestionBankId(questionBankId);
                    dto.setQuestionBankName(questionBankName);
                    dto.setTitle(processTitle(title));
                    dto.setAnswer(answer);
                    dto.setTrueAnswer(trueAnswer(answer));
                    dto.setJieXi(jieXi);
                    dto.setNote(note);
                    dto.setCategoryId(categoryId);
                    dto.setCourseId(courseId);
                    dto.setType(processType(dto.getTrueAnswer()));
                    dto.setIsLock(0);
                    list.add(dto);
                    break;
                }
                String nextVal = trim(nextRow.getCell(0).getStringCellValue());
                boolean endTitle = Pattern.matches(QuestionBankConstants.TITLE_PATTERN, nextVal);
                if (!endTitle) {
                    flag = 3;
                } else {
                    ExamQuestionBank dto = new ExamQuestionBank();
                    dto.setQuestionId(Identities.uuid2());
                    dto.setQuestionBankId(questionBankId);
                    dto.setQuestionBankName(questionBankName);
                    dto.setTitle(processTitle(title));
                    dto.setAnswer(answer);
                    dto.setTrueAnswer(trueAnswer(answer));
                    dto.setJieXi(jieXi);
                    dto.setNote(note);
                    dto.setCategoryId(categoryId);
                    dto.setCourseId(courseId);
                    dto.setType(processType(dto.getTrueAnswer()));
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
        model.addAttribute("successTotal", successTotal);
        return "forward:success";
    }


    private String processType(String trueAnswer) {
        int i = appearNumber(trueAnswer, QuestionBankConstants.ANSWER_TRUE_PATTERN);
//        if (i > 1) {
//            // 多选题
//            return "2";
//        } else if (
//                StringUtils.equals(trueAnswer, QuestionBankConstants.SHI_FEI_PATTERN1)
//                        || StringUtils.equals(trueAnswer, QuestionBankConstants.SHI_FEI_PATTERN2)
//                ) {
//            // 是非选题
//            return "3";
//        } else {
//            // 单选题
//            return "1";
//        }
        if (i > 1) {
            // 多选题
            return QuestionBankTypeEnum.MULTIPLE_QUESTION.getCode();
        } else if (
                StringUtils.equals(trueAnswer, QuestionBankConstants.SHI_FEI_PATTERN1)
                        || StringUtils.equals(trueAnswer, QuestionBankConstants.SHI_FEI_PATTERN2)
                ) {
            // 是非选题
            return QuestionBankTypeEnum.YES_NO_QUESTION.getCode();
        } else {
            // 单选题
            return QuestionBankTypeEnum.SINGLE_QUESTION.getCode();
        }
    }

    public int appearNumber(String srcText, String findText) {
        int count = 0;
        Pattern p = Pattern.compile(findText);
        Matcher m = p.matcher(srcText);
        while (m.find()) {
            count++;
        }
        return count;
    }

    /**
     * http://localhost:8080/exam/admin/upload/success
     *
     * @param model
     * @return
     */
    @RequestMapping("/success")
    public String success(Model model, HttpServletRequest request) {
        return "admin/successAdmin";
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
        return trim(nextRow.getCell(0).getStringCellValue());
    }


    /**
     * 正确答案
     *
     * @param stringCellValue
     * @return
     */
    private String trueAnswer(String stringCellValue) {
        if (StringUtils.containsAny(stringCellValue, QuestionBankConstants.ANSWER_PATTERN) ||
                StringUtils.containsAny(stringCellValue, QuestionBankConstants.ANSWER_TRUE_PATTERN)) {
            String ss = trim(stringCellValue);
            ss = StringUtils.substringAfter(ss, QuestionBankConstants.ANSWER_TRUE_PATTERN);
            ss = StringUtils.substringBefore(ss, QuestionBankConstants.ANSWER_PATTERN);
            ss = StringUtils.deleteWhitespace(ss).trim();
            return QuestionBankConstants.ANSWER_TRUE_PATTERN + ss;
        }
        return "";
    }

    private String trim(String val) {
        return val.replaceAll("  ", "")
                .replaceAll(" ", "")
                .replaceAll("   ", "")
                .replaceAll(" ", "")
                .trim();
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

    /**
     * 去题号
     *
     * @param title
     * @return
     */
    private String processTitle(String title) {
        int i = title.indexOf(".");
        return title.substring(i + 1, title.length());
    }
}


