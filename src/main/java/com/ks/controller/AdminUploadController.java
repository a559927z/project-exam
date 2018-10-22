package com.ks.controller;

import com.google.common.collect.Lists;
import com.ks.constants.QuestionBankConstants;
import com.ks.constants.QuestionBankTypeEnum;
import com.ks.dao.ExamQuestionBankMapper;
import com.ks.dao.ExamUserAnswerYaMapper;
import com.ks.dao.ExamUserInfoMapper;
import com.ks.dto.*;
import com.ks.service.UploadService;
import com.ks.utils.StringUtil;
import groovy.util.logging.Slf4j;
import net.chinahrd.utils.Identities;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private UploadService uploadService;

    @Autowired
    private ExamQuestionBankMapper examQuestionBankMapper;

    @Autowired
    private ExamUserInfoMapper examUserInfoMapper;


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

        createFile(file);

        // 合并题库
        ExamQuestionBankExample example = new ExamQuestionBankExample();
        example.createCriteria().andQuestionBankNameEqualTo(questionBankName);
        List<ExamQuestionBank> examQuestionBankList = examQuestionBankMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(examQuestionBankList)) {
            questionBankId = examQuestionBankList.get(0).getQuestionBankId();
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

    /**
     * 把上传文件写入指定的目录里
     *
     * @param file
     */
    private void createFile(MultipartFile file) {
        if (null == file) {
            throw new RuntimeException("上传附件为空");
        }
        // jar包的路径
        String filePath = "";
        if (!file.isEmpty()) {
            File temp = new File("");
            // 相对位置
            // filePath = temp.getAbsolutePath() + "\\" + file.getOriginalFilename();

            // 绝对位置
            filePath = uploadPath + File.separator + file.getOriginalFilename();
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
    }


    private String processType(String trueAnswer) {
        int i = appearNumber(trueAnswer, QuestionBankConstants.ANSWER_TRUE_PATTERN);
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

    /**
     * 员工上传
     *
     * @param model
     * @param file
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/userInfoUpload", method = {RequestMethod.GET, RequestMethod.POST})
    public String userInfoUpload(Model model,
                                 MultipartFile file,
                                 HttpServletRequest request, HttpServletResponse response) throws IOException, InvalidFormatException {

        createFile(file);

        List<ExamUserInfoDto> rs = Lists.newArrayList();
        Workbook sheets = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = sheets.getSheetAt(0);
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            Cell accountCell = row.getCell(0);
            Cell passwordCell = row.getCell(1);
            Cell cnNameCell = row.getCell(2);
            Cell cardIdCell = row.getCell(3);

            if (null == accountCell) {
                continue;
            }

            Cell 银行业法律法规与综合能力Cell = row.getCell(4);
            Cell 个人理财Cell = row.getCell(5);
            Cell 个人贷款Cell = row.getCell(6);
            Cell 公司信贷Cell = row.getCell(7);
            Cell 风险管理Cell = row.getCell(8);
            Cell 银行管理Cell = row.getCell(9);
            Cell 基金法律法规_职业道德与业务规范Cell = row.getCell(10);
            Cell 证券投资基金基础知识Cell = row.getCell(11);
            Cell 私募股权投资基金基础知识Cell = row.getCell(12);
            Cell 证券市场基本法律法规Cell = row.getCell(13);
            Cell 金融市场基础知识Cell = row.getCell(14);


            ExamUserInfoDto dto = new ExamUserInfoDto();
            dto.setAccount(getCellValue(accountCell));
            dto.setPassword(getCellValue(passwordCell));
            dto.setCnName(getCellValue(cnNameCell));
            dto.setCardId(getCellValue(cardIdCell));
            dto.setV1(getCellValue(银行业法律法规与综合能力Cell));
            dto.setV2(getCellValue(个人理财Cell));
            dto.setV3(getCellValue(个人贷款Cell));
            dto.setV4(getCellValue(公司信贷Cell));
            dto.setV5(getCellValue(风险管理Cell));
            dto.setV6(getCellValue(银行管理Cell));
            dto.setV7(getCellValue(基金法律法规_职业道德与业务规范Cell));
            dto.setV8(getCellValue(证券投资基金基础知识Cell));
            dto.setV9(getCellValue(私募股权投资基金基础知识Cell));
            dto.setV10(getCellValue(证券市场基本法律法规Cell));
            dto.setV11(getCellValue(金融市场基础知识Cell));

            rs.add(dto);
        }
        int i = saveUserInfo(rs);

        if (i > 0) {
            model.addAttribute("isSuccess", true);
        } else {
            model.addAttribute("isSuccess", false);
        }
        model.addAttribute("total", i);
        return "forward:/admin/user/index";
    }

    /**
     * 入库
     *
     * @param list
     * @return 入库条数
     */
    private int saveUserInfo(List<ExamUserInfoDto> list) {

        List<ExamUserInfo> examUserInfoList = examUserInfoMapper.selectByExample(null);

        int rs = 0;
        for (ExamUserInfoDto dto : list) {

            String account = dto.getAccount();
            boolean exist = false;
            for (ExamUserInfo n : examUserInfoList) {
                if (StringUtils.equals(account, n.getAccount())) {
                    exist = true;
                }
            }

            ExamUserInfo examDto = new ExamUserInfo();
            BeanUtils.copyProperties(dto, examDto);
            if (exist) {
                // 存在就更新
                ExamUserInfoExample uiExample = new ExamUserInfoExample();
                uiExample.createCriteria().andAccountEqualTo(dto.getAccount());
                rs += examUserInfoMapper.updateByExample(examDto, uiExample);
            } else {
                rs += examUserInfoMapper.insertSelective(examDto);
            }
        }
        return rs;
    }


    private String getCellValue(Cell cell) {
        if (null != cell) {
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            return trim(cell.getStringCellValue());
        }
        return "";

    }


}


