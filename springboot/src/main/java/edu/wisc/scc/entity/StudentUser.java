package edu.wisc.scc.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import common.BaseEntity;
import lombok.Data;

/**
 * student user(StudentUser)表实体类
 *
 * @author 高祈
 * @since 2021-03-17 23:15:15
 */
@Data
@TableName("t_student_user")
public class StudentUser extends BaseEntity<StudentUser> {
    //user name
    private String userName;

    //password
    private String userPwd;

    //student identity
    private String studentIdentity;

    //email
    private String email;

    //first name
    private String firstName;

    //last name
    private String lastName;

}
