package edu.wisc.scc.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import common.BaseEntity;
import lombok.Data;

/**
 * subject info(SubjectInfo)表实体类
 *
 * @author 蔺春华
 * @since 2021-03-28 11:34:51
 */
@Data
@TableName("t_subject_info")
public class SubjectInfo extends BaseEntity<SubjectInfo> {
    //subject name
    private String subjectName;

}
