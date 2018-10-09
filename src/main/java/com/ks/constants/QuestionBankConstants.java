package com.ks.constants;

/**
 * Title: ${type_name} <br/>
 * <p>
 * Description: <br/>
 *
 * @author jxzhang
 * @DATE 2018年09月11日 14:00
 * @Verdion 1.0 版本
 * ${tags}
 */
public interface QuestionBankConstants {
    String TITLE_PATTERN = "^\\d+\\.\\S+";
    String ANSWER_PATTERN = "☐";
    String ANSWER_TRUE_PATTERN = "☑";
    String JIE_XI_PATTERN = "解析:";
    String NOTE_PATTERN = "押题说明:";
    String SHI_FEI_PATTERN1 = "☑正确";
    String SHI_FEI_PATTERN2 = "☑错误";

    Integer NOT_USEING = 0;

    /**
     * 学员在“考前押题里”有操作记录
     */
    Integer USER_ANSWER_YA_USEING = -9999;

    /**
     * 已经被列入押题管理
     */
    Integer QUESTION_BANK_YA_USEING = -8888;
}
