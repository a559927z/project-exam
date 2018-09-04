package com.ks.controller;

import com.ks.utils.StringUtil;
import groovy.util.logging.Slf4j;
import net.chinahrd.utils.ExcelUtil;
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
@RequestMapping("/admin/upload")
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

        Workbook sheets = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = sheets.getSheetAt(0);
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            Cell cell = row.getCell(0);
            System.out.println(cell);

            String stringCellValue = cell.getStringCellValue();
            if (StringUtil.containsAny(stringCellValue, "☐")) {
                System.out.println("答案=====>" + stringCellValue);
                if (StringUtil.containsAny(stringCellValue, "☑")) {
                    String ss = stringCellValue
                            .replaceAll(" ", "")
                            .replaceAll("   ", "");

                    ss = StringUtil.substringAfter(ss, "☑");
                    ss = StringUtil.substringBefore(ss, "☐");
                    ss = StringUtil.deleteWhitespace(ss).trim();
                    System.out.println("正确答案=====>" + ss);
                }
            }
        }
        return "";
    }

    public static void main(String[] args) {
        String ss = "☐I、II   ☑I、II、III   ☐II、III   ☐I、III "
                .replaceAll(" ", "")
                .replaceAll("   ", "");

        ss = StringUtil.substringAfter(ss, "☑");
        ss = StringUtil.substringBefore(ss, "☐");
        ss = StringUtil.deleteWhitespace(ss).trim();

        System.out.println(ss);
    }
}
