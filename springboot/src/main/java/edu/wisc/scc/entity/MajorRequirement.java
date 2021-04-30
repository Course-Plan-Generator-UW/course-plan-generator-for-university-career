package edu.wisc.scc.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import common.BaseEntity;
import lombok.Data;

/**
 * major requirement(MajorRequirement)表实体类
 *
 * @author 蔺春华
 * @since 2021-04-07 20:10:56
 */
@Data
@TableName("t_major_requirement")
public class MajorRequirement extends BaseEntity<MajorRequirement> {
    //major name
    private String majorName;

    //course name
    private String courseName;

    //course section
    private String courseSection;

    //credits need in section
    private Integer creditsNeedInSection;

}
