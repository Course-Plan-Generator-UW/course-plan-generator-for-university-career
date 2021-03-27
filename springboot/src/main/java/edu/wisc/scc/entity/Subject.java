package edu.wisc.scc.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import common.BaseEntity;
import lombok.Data;

/**
 * (Subject)表实体类
 * 课程分类
 * @author Gao Qi
 * @since 2021-03-24 00:32:26
 */
@Data
@TableName("subject")
public class Subject extends BaseEntity<Subject> {
    //The unique code given by the university of this subject.
    private String code;

    //The name of the subject.
    private String name;

    //The abbreviated name of this subject.
    private String abbreviation;

}
