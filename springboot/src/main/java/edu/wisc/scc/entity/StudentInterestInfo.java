package edu.wisc.scc.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import common.BaseEntity;
import lombok.Data;

/**
 * interest info(StudentInterestInfo)表实体类
 *
 * @author 蔺春华
 * @since 2021-03-28 11:35:10
 */
@Data
@TableName("t_student_interest_info")
public class StudentInterestInfo extends BaseEntity<StudentInterestInfo> {
    //subject name
    private String subjectName;

    //student name
    private String studentId;


    private Integer interestLevel;

}
