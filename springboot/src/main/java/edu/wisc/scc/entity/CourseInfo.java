package edu.wisc.scc.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import common.BaseEntity;
import lombok.Data;

/**
 * course info(CourseInfo)表实体类
 *
 * @author Gao Qi
 * @since 2021-03-25 20:06:17
 */
@Data
@TableName("t_course_info")
public class CourseInfo extends BaseEntity<CourseInfo> {
    //course name
    private String courseName;

    //student count
    private Integer totalStudents;

    //subject name
    private String subject;

    //avg gpa
    private Double avgGpa;


    private Double avgRate;

}
