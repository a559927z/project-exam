package com.ks.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.ks.utils.StringUtil;
import groovy.util.logging.Slf4j;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
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
    @ResponseBody
    @RequestMapping(value = "/parseXls2Dto", method = {RequestMethod.GET, RequestMethod.POST})
    public String parseXls2Dto(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException, InvalidFormatException {
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
        List<Data2DTO> list = Lists.newArrayList();
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
                    System.out.println("title===>" + title);
                    System.out.println("answer==>" + answer);
                    System.out.println("jieXi===>" + jieXi);
                    System.out.println("note====>" + note);
                    Data2DTO dto = new Data2DTO();
                    dto.setTitle(title);
                    dto.setAnswer(answer);
                    dto.setTrueAnswer(trueAnswer(answer));
                    dto.setJieXi(jieXi);
                    dto.setNote(note);
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
                    System.out.println("title===>" + title);
                    System.out.println("answer==>" + answer);
                    System.out.println("jieXi===>" + jieXi);
                    System.out.println("note====>" + note);
                    System.out.println("================================================================================================================================");
                    Data2DTO dto = new Data2DTO();
                    dto.setTitle(title);
                    dto.setAnswer(answer);
                    dto.setTrueAnswer(trueAnswer(answer));
                    dto.setJieXi(jieXi);
                    dto.setNote(note);
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
        return JSON.toJSONString(list);
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
        if (StringUtil.containsAny(stringCellValue, "☐")) {
//            System.out.println("答案=====>" + stringCellValue);
            if (StringUtil.containsAny(stringCellValue, "☑")) {
                String ss = stringCellValue
                        .replaceAll(" ", "")
                        .replaceAll("   ", "");
                ss = StringUtil.substringAfter(ss, ANSWER_TRUE_PATTERN);
                ss = StringUtil.substringBefore(ss, ANSWER_PATTERN);
                ss = StringUtil.deleteWhitespace(ss).trim();
//                System.out.println("正确答案=====>" + ss);
                return ss;
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

        String stringCellValue = "563.xxxx";

        boolean matches = Pattern.matches("^\\d+\\.\\S+", stringCellValue);
        System.out.println(matches);
    }
}


@NoArgsConstructor
class Data2DTO {
    private String title;
    private String answer;
    private String jieXi;
    private String note;
    private String trueAnswer;

    public String getTrueAnswer() {
        return trueAnswer;
    }

    public void setTrueAnswer(String trueAnswer) {
        this.trueAnswer = trueAnswer;
    }

    public String getJieXi() {
        return jieXi;
    }

    public void setJieXi(String jieXi) {
        this.jieXi = jieXi;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

