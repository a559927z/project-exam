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
public enum QuestionBankCategoryEnum {
    BANK("1", "银行从业") {
        @Override
        public ItemEnum L1() {
            return ItemEnum.L1;
        }

        @Override
        public ItemEnum L2() {
            return null;
        }

        @Override
        public ItemEnum L3() {
            return null;
        }
    },
    FUND("2", "基金从业") {
        @Override
        public ItemEnum L1() {
            return null;
        }

        @Override
        public ItemEnum L2() {
            return null;
        }

        @Override
        public ItemEnum L3() {
            return null;
        }
    },
    SECURITY("3", "证券从业") {
        @Override
        public ItemEnum L1() {
            return null;
        }

        @Override
        public ItemEnum L2() {
            return null;
        }

        @Override
        public ItemEnum L3() {
            return null;
        }
    };

    private String code;
    private String name;

    public static String getNameByCode(String code) {
        QuestionBankCategoryEnum[] values = QuestionBankCategoryEnum.values();
        for (QuestionBankCategoryEnum item : values) {
            if (StringUtils.equals(item.getCode(), code)) {
                return item.getName();
            }
        }
        log.error("不存在");
        return null;
    }

    public abstract ItemEnum L1();

    public abstract ItemEnum L2();

    public abstract ItemEnum L3();

    @AllArgsConstructor
    @Getter
    public enum ItemEnum {
        L1("1", "1", "银行业法律法规与综合能力"),
        L2("2", "1", "个人理财"),
        L3("3", "1", "个人贷款"),
        L4("4", "1", "公司信贷"),
        L5("5", "1", "风险管理"),
        L6("6", "1", "银行管"),


        L7("7", "2", "基金法律法规、职业道德与业务规范"),
        L8("8", "2", "证券投资基金基础知识"),
        L9("9", "2", "私募股权投资基金基础知识"),


        L10("10", "3", "证券市场基本法律法规"),
        L11("11", "3", "金融市场基础知识");
        private String code;
        private String parentCode;
        private String name;
    }

    public static ItemEnum getItemEnum(String parentCode, String subCode) {
        QuestionBankCategoryEnum[] parentEnums = QuestionBankCategoryEnum.values();
        for (QuestionBankCategoryEnum parentEnum : parentEnums) {
            if (StringUtils.equals(parentEnum.getCode(), parentCode)) {
                if (StringUtils.equals(parentEnum.L1().getCode(), subCode)) {
                    return parentEnum.L1();
                }
            }
        }
        return null;
    }

}
