package com.ks.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * Title: ${type_name} <br/>
 * <p>
 * Description: <br/>
 *
 * @author jxzhang
 * @DATE 2018年09月11日 16:36
 * @Verdion 1.0 版本
 * ${tags}
 */
@Slf4j
@AllArgsConstructor
@Getter
public enum QuestionBankCourseEnum {
    L1("1", "银行业法律法规与综合能力"),
    L2("2", "个人理财"),
    L3("3", "个人贷款"),
    L4("4", "公司信贷"),
    L5("5", "风险管理"),
    L6("6", "银行管"),
    L7("7", "基金法律法规、职业道德与业务规范"),
    L8("8", "证券投资基金基础知识"),
    L9("9", "私募股权投资基金基础知识"),
    L10("10", "证券市场基本法律法规"),
    L11("11", "金融市场基础知识");

    private String code;
    private String name;

    public static String getNameByCode(String code) {
        QuestionBankCourseEnum[] values = QuestionBankCourseEnum.values();
        for (QuestionBankCourseEnum item : values) {
            if (StringUtils.equals(item.getCode(), code)) {
                return item.getName();
            }
        }
        log.error("不存在");
        return null;
    }
}
