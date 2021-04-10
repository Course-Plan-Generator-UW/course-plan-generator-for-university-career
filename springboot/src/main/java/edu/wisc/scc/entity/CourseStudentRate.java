package edu.wisc.scc.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import common.BaseEntity;
import lombok.Data;

/**
 * t_course_student_rate(CourseStudentRate)表实体类
 *
 * @author 蔺春华
 * @since 2021-04-10 00:21:33
 */
@Data
@TableName("t_course_student_rate")
public class CourseStudentRate extends BaseEntity<CourseStudentRate> {
    //course name
    private String courseName;

    //student id
    private String studentId;

    //rate
    private Double rate;

}
