package edu.wisc.scc.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import common.BaseEntity;
import lombok.Data;

/**
 * (Schedule)表实体类
 *
 * @author Gao Qi
 * @since 2021-03-24 06:43:54
 */
@Data
@TableName("schedule")
public class Schedule extends BaseEntity<Schedule> {
    //The start time of this schedule in minutes.
    private Integer startTime;

    //The end time of this schedule in minutes.
    private Integer endTime;


    private String mon;


    private String tues;


    private String wed;


    private String thurs;


    private String fri;


    private String sat;


    private String sun;

}
