package edu.wisc.scc.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import common.BaseEntity;
import lombok.Data;

/**
 * (Course)表实体类
 *
 * @author Gao Qi
 * @since 2021-03-24 06:23:16
 */
@Data
@TableName("course")
public class Course extends BaseEntity<Course> {
    //The official, latest name of the course (sometimes null if we do not have that
    private String name;

    //The number of the course (not unique).
    private Integer number;

}
