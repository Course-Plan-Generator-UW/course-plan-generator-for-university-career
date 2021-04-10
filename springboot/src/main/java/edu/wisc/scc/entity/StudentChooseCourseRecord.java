package edu.wisc.scc.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import common.BaseEntity;
import lombok.Data;

/**
 * student choose course record(StudentChooseCourseRecord)表实体类
 *
 * @author 蔺春华
 * @since 2021-04-07 21:06:46
 */
@Data
@TableName("t_student_choose_course_record")
public class StudentChooseCourseRecord extends BaseEntity<StudentChooseCourseRecord> {
    //student id
    private String studentId;

    //course name
    private String courseName;

    //which year
    private Integer whichYear;

    //semester
    private String semester;

}
