package edu.wisc.scc.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import common.BaseEntity;
import lombok.Data;

/**
 * (Instructor)表实体类
 *
 * @author Gao Qi
 * @since 2021-03-24 07:19:09
 */
@Data
@TableName("instructor")
public class Instructor extends BaseEntity<Instructor> {
    //The university given instructor id.
    private Long insNumber;

    //The name of the instructor.
    private String name;

}
