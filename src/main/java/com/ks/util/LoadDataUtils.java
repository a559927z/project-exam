package com.ks.util;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Title: ${type_name} <br/>
 * <p>
 * Description: <br/>
 *
 * @author jxzhang
 * @DATE 2018年09月04日 20:00
 * @Verdion 1.0 版本
 * ${tags}
 */
public class LoadDataUtils {

    public static void read() throws IOException {
        String filePath = "D:\\tmp\\123.txt";
        File file = new File(filePath);
        String content = FileUtils.readFileToString(file);
//        System.out.println(content);
//        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
//        StringBuilder sb = new StringBuilder();//定义一个字符串缓存，将字符串存放缓存中
//        String s = "";
//        while ((s = bufferedReader.readLine()) != null) {//逐行读取文件内容，不读取换行符和末尾的空格
//            sb.append(s + "\n");//将读取的字符串添加换行符后累加存放在缓存中
//            System.out.println(s);
//        }
//        bufferedReader.close();
//        String str = sb.toString();
//        System.out.println(str);
        asyc(content);
    }

    private static void asyc(String content) {
        char[] chars = content.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            DataDTO dataDTO = new DataDTO();
            String title = "";
            Character aChar = chars[i];
            title += aChar.toString();

            if (StringUtils.equals("☑", aChar.toString())
                    || StringUtils.equals("☐", aChar.toString())) {
                dataDTO.setAnswer(aChar.toString());
            } else {
                dataDTO.setTitle(title);
            }


        }
        System.out.println(chars);

    }

    public static void main(String[] args) throws IOException {
        LoadDataUtils.read();
    }


}


@NoArgsConstructor
class DataDTO {
    private String title;
    private String answer;

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
