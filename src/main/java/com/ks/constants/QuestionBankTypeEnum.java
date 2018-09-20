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
public enum QuestionBankTypeEnum {
    SINGLE_QUESTION("1", "单选题"),
    MULTIPLE_QUESTION("2", "多选题"),
    YES_NO_QUESTION("3", "是非题");

    private String code;
    private String name;

    public static String getNameByCode(String code) {
        QuestionBankTypeEnum[] values = QuestionBankTypeEnum.values();
        for (QuestionBankTypeEnum item : values) {
            if (StringUtils.equals(item.getCode(), code)) {
                return item.getName();
            }
        }
        log.error("不存在");
        return null;
    }
}
