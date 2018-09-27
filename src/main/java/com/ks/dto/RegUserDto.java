package com.ks.dto;

import lombok.Data;

/**
 * Title: ${type_name} <br/>
 * <p>
 * Description: <br/>
 *
 * @author jxzhang
 * @DATE 2018年09月27日 11:45
 * @Verdion 1.0 版本
 * ${tags}
 */
@Data
public class RegUserDto {

    private String phone;
    private String pwd;
    private String verificationCode;
}
