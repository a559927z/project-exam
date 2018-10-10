package com.ks.vo;

import lombok.Data;

import java.util.List;

/**
 * Title: ${type_name} <br/>
 * <p>
 * Description: <br/>
 *
 * @author jxzhang
 * @DATE 2018年10月10日 10:31
 * @Verdion 1.0 版本
 * ${tags}
 */
@Data
public class AnswerVo {

    /**
     * 题号
     */
    private int no;
    private String questionBankId;
    private String questionId;
    private String title;
    private List<String> answer;
    private String trueAnswer;
    private String jieXi;

    private String type;

}
