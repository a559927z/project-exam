package com.ks.vo;

import lombok.Data;

/**
 * Title: ${type_name} <br/>
 * <p>
 * Description: <br/>
 *
 * @author jxzhang
 * @DATE 2019年03月04日 15:16
 * @Verdion 1.0 版本
 * ${tags}
 */
@Data
public class WxLoginVo {
    String code;

    String openid;
    String session_key;
    String unionid;
    String errcode;
    String errmsg;
}
