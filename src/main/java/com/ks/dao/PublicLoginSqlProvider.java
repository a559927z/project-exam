package com.ks.dao;

import com.ks.dto.PublicLogin;
import com.ks.dto.PublicLoginExample.Criteria;
import com.ks.dto.PublicLoginExample.Criterion;
import com.ks.dto.PublicLoginExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class PublicLoginSqlProvider {

    public String countByExample(PublicLoginExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("public_login");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(PublicLoginExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("public_login");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(PublicLogin record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("public_login");
        
        if (record.getCustomerId() != null) {
            sql.VALUES("customer_id", "#{customerId,jdbcType=VARCHAR}");
        }
        
        if (record.getAccessId() != null) {
            sql.VALUES("access_id", "#{accessId,jdbcType=VARCHAR}");
        }
        
        if (record.getPassword() != null) {
            sql.VALUES("password", "#{password,jdbcType=VARCHAR}");
        }
        
        if (record.getIsLock() != null) {
            sql.VALUES("is_lock", "#{isLock,jdbcType=BIT}");
        }
        
        return sql.toString();
    }

    public String selectByExample(PublicLoginExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("customer_id");
        } else {
            sql.SELECT("customer_id");
        }
        sql.SELECT("access_id");
        sql.SELECT("password");
        sql.SELECT("is_lock");
        sql.FROM("public_login");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        PublicLogin record = (PublicLogin) parameter.get("record");
        PublicLoginExample example = (PublicLoginExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("public_login");
        
        if (record.getCustomerId() != null) {
            sql.SET("customer_id = #{record.customerId,jdbcType=VARCHAR}");
        }
        
        if (record.getAccessId() != null) {
            sql.SET("access_id = #{record.accessId,jdbcType=VARCHAR}");
        }
        
        if (record.getPassword() != null) {
            sql.SET("password = #{record.password,jdbcType=VARCHAR}");
        }
        
        if (record.getIsLock() != null) {
            sql.SET("is_lock = #{record.isLock,jdbcType=BIT}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("public_login");
        
        sql.SET("customer_id = #{record.customerId,jdbcType=VARCHAR}");
        sql.SET("access_id = #{record.accessId,jdbcType=VARCHAR}");
        sql.SET("password = #{record.password,jdbcType=VARCHAR}");
        sql.SET("is_lock = #{record.isLock,jdbcType=BIT}");
        
        PublicLoginExample example = (PublicLoginExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(PublicLogin record) {
        SQL sql = new SQL();
        sql.UPDATE("public_login");
        
        if (record.getPassword() != null) {
            sql.SET("password = #{password,jdbcType=VARCHAR}");
        }
        
        if (record.getIsLock() != null) {
            sql.SET("is_lock = #{isLock,jdbcType=BIT}");
        }
        
        sql.WHERE("customer_id = #{customerId,jdbcType=VARCHAR}");
        sql.WHERE("access_id = #{accessId,jdbcType=VARCHAR}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, PublicLoginExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}