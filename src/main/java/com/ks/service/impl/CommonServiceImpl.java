package com.ks.service.impl;

import com.alibaba.fastjson.JSON;
import com.ks.dao.PublicLogMapper;
import com.ks.dto.PublicLog;
import com.ks.service.CommonService;
import net.chinahrd.utils.Identities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Title: ${type_name} <br/>
 * <p>
 * Description: <br/>
 *
 * @author jxzhang
 * @DATE 2018年11月08日 15:27
 * @Verdion 1.0 版本
 * ${tags}
 */
@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private PublicLogMapper publicLogMapper;

    @Override
    public void saveLog(Object content, String module, String optId) {
        PublicLog dto = new PublicLog();
        dto.setId(Identities.uuid2());
        dto.setContent(JSON.toJSONString(content));
        dto.setModule(module);
        dto.setCreatedBy(optId);
        dto.setCreatedDate(new Date());
        dto.setCustomerId("exam");
        publicLogMapper.insert(dto);
    }


    @Override
    public void deleteLog() {
        publicLogMapper.deleteByExample(null);
    }
}
