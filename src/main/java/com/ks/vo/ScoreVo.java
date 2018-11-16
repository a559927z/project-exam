package com.ks.vo;

import lombok.Data;

/**
 * Title: ${type_name} <br/>
 * <p>
 * Description: <br/>
 *
 * @author jxzhang
 * @DATE 2018年10月15日 08:59
 * @Verdion 1.0 版本
 * ${tags}
 */
@Data
public class ScoreVo {

    /**
     * 题目类型
     */
    private String type;
    /**
     * 对题数
     */
    private int right;
    /**
     * 错题数
     */
    private int wrong;
    /**
     * 正确率= 正确数/已完成数
     * ArithUtil.div(sRight, (sRight + sWrong), 2) + "%"
     */
    private String ratioStr;

    /**
     * 分值 = （对题数+错题数） * 题目类型分数
     */
    private double score;

    /**
     * 得分 = 对题数 * 题目类型分数
     */
    private double myScore;
}
