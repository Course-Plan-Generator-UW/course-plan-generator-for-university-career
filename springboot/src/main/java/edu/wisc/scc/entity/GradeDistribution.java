package edu.wisc.scc.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import common.BaseEntity;
import lombok.Data;

/**
 * (GradeDistribution)表实体类
 *
 * @author Gao Qi
 * @since 2021-03-24 06:38:48
 */
@Data
@TableName("grade_distribution")
public class GradeDistribution extends BaseEntity<GradeDistribution> {
    //The uuid of the course offering this belongs to.
    private String courseOfferingUuid;

    //The section number of the section this belongs to.
    private Integer sectionNumber;


    private Integer nCount;


    private Integer cCount;


    private Integer fCount;


    private Integer crCount;


    private Integer pCount;


    private Integer nwCount;


    private Integer otherCount;


    private Integer bcCount;


    private Integer uCount;


    private Integer dCount;


    private Integer abCount;


    private Integer aCount;


    private Integer sCount;


    private Integer iCount;


    private Integer bCount;


    private Integer nrCount;

}
