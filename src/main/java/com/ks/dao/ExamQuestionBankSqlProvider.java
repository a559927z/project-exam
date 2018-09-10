package com.ks.dao;

import com.ks.dto.ExamQuestionBank;
import com.ks.dto.ExamQuestionBankExample.Criteria;
import com.ks.dto.ExamQuestionBankExample.Criterion;
import com.ks.dto.ExamQuestionBankExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class ExamQuestionBankSqlProvider {

    public String countByExample(ExamQuestionBankExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("exam_question_bank");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(ExamQuestionBankExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("exam_question_bank");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(ExamQuestionBank record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("exam_question_bank");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getTitle() != null) {
            sql.VALUES("title", "#{title,jdbcType=VARCHAR}");
        }
        
        if (record.getAnswer() != null) {
            sql.VALUES("answer", "#{answer,jdbcType=VARCHAR}");
        }
        
        if (record.getTrueAnswer() != null) {
            sql.VALUES("true_answer", "#{trueAnswer,jdbcType=VARCHAR}");
        }
        
        if (record.getJieXi() != null) {
            sql.VALUES("jie_xi", "#{jieXi,jdbcType=VARCHAR}");
        }
        
        if (record.getNote() != null) {
            sql.VALUES("note", "#{note,jdbcType=VARCHAR}");
        }
        
        if (record.getQuestionBankId() != null) {
            sql.VALUES("question_bank_id", "#{questionBankId,jdbcType=VARCHAR}");
        }
        
        if (record.getQuestionBankName() != null) {
            sql.VALUES("question_bank_name", "#{questionBankName,jdbcType=VARCHAR}");
        }
        
        if (record.getCategoryId() != null) {
            sql.VALUES("category_id", "#{categoryId,jdbcType=VARCHAR}");
        }
        
        if (record.getCourseId() != null) {
            sql.VALUES("course_id", "#{courseId,jdbcType=VARCHAR}");
        }
        
        if (record.getIsLock() != null) {
            sql.VALUES("is_lock", "#{isLock,jdbcType=INTEGER}");
        }
        
        if (record.getCreatedBy() != null) {
            sql.VALUES("created_by", "#{createdBy,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedDate() != null) {
            sql.VALUES("created_date", "#{createdDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdatedBy() != null) {
            sql.VALUES("updated_by", "#{updatedBy,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdatedDate() != null) {
            sql.VALUES("updated_date", "#{updatedDate,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String selectByExample(ExamQuestionBankExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("title");
        sql.SELECT("answer");
        sql.SELECT("true_answer");
        sql.SELECT("jie_xi");
        sql.SELECT("note");
        sql.SELECT("question_bank_id");
        sql.SELECT("question_bank_name");
        sql.SELECT("category_id");
        sql.SELECT("course_id");
        sql.SELECT("is_lock");
        sql.SELECT("created_by");
        sql.SELECT("created_date");
        sql.SELECT("updated_by");
        sql.SELECT("updated_date");
        sql.FROM("exam_question_bank");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        ExamQuestionBank record = (ExamQuestionBank) parameter.get("record");
        ExamQuestionBankExample example = (ExamQuestionBankExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("exam_question_bank");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getTitle() != null) {
            sql.SET("title = #{record.title,jdbcType=VARCHAR}");
        }
        
        if (record.getAnswer() != null) {
            sql.SET("answer = #{record.answer,jdbcType=VARCHAR}");
        }
        
        if (record.getTrueAnswer() != null) {
            sql.SET("true_answer = #{record.trueAnswer,jdbcType=VARCHAR}");
        }
        
        if (record.getJieXi() != null) {
            sql.SET("jie_xi = #{record.jieXi,jdbcType=VARCHAR}");
        }
        
        if (record.getNote() != null) {
            sql.SET("note = #{record.note,jdbcType=VARCHAR}");
        }
        
        if (record.getQuestionBankId() != null) {
            sql.SET("question_bank_id = #{record.questionBankId,jdbcType=VARCHAR}");
        }
        
        if (record.getQuestionBankName() != null) {
            sql.SET("question_bank_name = #{record.questionBankName,jdbcType=VARCHAR}");
        }
        
        if (record.getCategoryId() != null) {
            sql.SET("category_id = #{record.categoryId,jdbcType=VARCHAR}");
        }
        
        if (record.getCourseId() != null) {
            sql.SET("course_id = #{record.courseId,jdbcType=VARCHAR}");
        }
        
        if (record.getIsLock() != null) {
            sql.SET("is_lock = #{record.isLock,jdbcType=INTEGER}");
        }
        
        if (record.getCreatedBy() != null) {
            sql.SET("created_by = #{record.createdBy,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedDate() != null) {
            sql.SET("created_date = #{record.createdDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdatedBy() != null) {
            sql.SET("updated_by = #{record.updatedBy,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdatedDate() != null) {
            sql.SET("updated_date = #{record.updatedDate,jdbcType=TIMESTAMP}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("exam_question_bank");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("title = #{record.title,jdbcType=VARCHAR}");
        sql.SET("answer = #{record.answer,jdbcType=VARCHAR}");
        sql.SET("true_answer = #{record.trueAnswer,jdbcType=VARCHAR}");
        sql.SET("jie_xi = #{record.jieXi,jdbcType=VARCHAR}");
        sql.SET("note = #{record.note,jdbcType=VARCHAR}");
        sql.SET("question_bank_id = #{record.questionBankId,jdbcType=VARCHAR}");
        sql.SET("question_bank_name = #{record.questionBankName,jdbcType=VARCHAR}");
        sql.SET("category_id = #{record.categoryId,jdbcType=VARCHAR}");
        sql.SET("course_id = #{record.courseId,jdbcType=VARCHAR}");
        sql.SET("is_lock = #{record.isLock,jdbcType=INTEGER}");
        sql.SET("created_by = #{record.createdBy,jdbcType=VARCHAR}");
        sql.SET("created_date = #{record.createdDate,jdbcType=TIMESTAMP}");
        sql.SET("updated_by = #{record.updatedBy,jdbcType=VARCHAR}");
        sql.SET("updated_date = #{record.updatedDate,jdbcType=TIMESTAMP}");
        
        ExamQuestionBankExample example = (ExamQuestionBankExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(ExamQuestionBank record) {
        SQL sql = new SQL();
        sql.UPDATE("exam_question_bank");
        
        if (record.getTitle() != null) {
            sql.SET("title = #{title,jdbcType=VARCHAR}");
        }
        
        if (record.getAnswer() != null) {
            sql.SET("answer = #{answer,jdbcType=VARCHAR}");
        }
        
        if (record.getTrueAnswer() != null) {
            sql.SET("true_answer = #{trueAnswer,jdbcType=VARCHAR}");
        }
        
        if (record.getJieXi() != null) {
            sql.SET("jie_xi = #{jieXi,jdbcType=VARCHAR}");
        }
        
        if (record.getNote() != null) {
            sql.SET("note = #{note,jdbcType=VARCHAR}");
        }
        
        if (record.getQuestionBankId() != null) {
            sql.SET("question_bank_id = #{questionBankId,jdbcType=VARCHAR}");
        }
        
        if (record.getQuestionBankName() != null) {
            sql.SET("question_bank_name = #{questionBankName,jdbcType=VARCHAR}");
        }
        
        if (record.getCategoryId() != null) {
            sql.SET("category_id = #{categoryId,jdbcType=VARCHAR}");
        }
        
        if (record.getCourseId() != null) {
            sql.SET("course_id = #{courseId,jdbcType=VARCHAR}");
        }
        
        if (record.getIsLock() != null) {
            sql.SET("is_lock = #{isLock,jdbcType=INTEGER}");
        }
        
        if (record.getCreatedBy() != null) {
            sql.SET("created_by = #{createdBy,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedDate() != null) {
            sql.SET("created_date = #{createdDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdatedBy() != null) {
            sql.SET("updated_by = #{updatedBy,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdatedDate() != null) {
            sql.SET("updated_date = #{updatedDate,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, ExamQuestionBankExample example, boolean includeExamplePhrase) {
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