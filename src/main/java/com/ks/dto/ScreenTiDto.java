package com.ks.dto;

import lombok.Data;

/**
 * Title: ${type_name} <br/>
 * <p>
 * Description: <br/>
 *
 * @author jxzhang
 * @DATE 2018年10月10日 09:55
 * @Verdion 1.0 版本
 * ${tags}
 */
@Data
public class ScreenTiDto {

    private String questionBankId;
    private int type;
    private int total;
    private boolean notDo;
}
