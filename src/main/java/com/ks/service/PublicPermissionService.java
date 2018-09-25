package com.ks.service;

import com.ks.dto.PublicUserInfo;

/**
 * Title: ${type_name} <br/>
 * <p>
 * Description: <br/>
 *
 * @author jxzhang
 * @DATE 2018年09月25日 22:02
 * @Verdion 1.0 版本
 * ${tags}
 */
public interface PublicPermissionService {

    PublicUserInfo findByUsername(String username);
}
