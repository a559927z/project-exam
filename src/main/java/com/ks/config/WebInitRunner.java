package com.ks.config;

import com.ks.dao.PublicUserInfoMapper;
import com.ks.dto.PublicUserInfo;
import com.ks.dto.PublicUserInfoExample;
import com.ks.utils.cache.LoadingCacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Title: ${type_name} <br/>
 * <p>
 * Description: <br/>
 *
 * @author jxzhang
 * @DATE 2018年09月29日 14:09
 * @Verdion 1.0 版本
 * ${tags}
 */
@Component
@Order(100)
public class WebInitRunner implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private PublicUserInfoMapper publicUserInfoMapper;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
//        PublicUserInfoExample example = new PublicUserInfoExample();
//        PublicUserInfoExample.Criteria criteria = example.createCriteria().andStateEqualTo(0);
//        List<PublicUserInfo> exists = publicUserInfoMapper.selectByExample(example);
//        rs = saveLogin(response, requestDto, rs, exists);
//        if (rs.getK()) {
//            LoadingCacheUtil.getInstance().save(enName, exists.get(0));
//        }
    }
}
