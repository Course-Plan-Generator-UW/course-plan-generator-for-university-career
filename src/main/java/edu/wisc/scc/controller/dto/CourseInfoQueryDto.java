package edu.wisc.scc.controller.dto;

import edu.wisc.scc.entity.CourseInfo;
import lombok.Data;

@Data
public class CourseInfoQueryDto extends CourseInfo {
    Integer totalStudentsLimitMax;
    Integer totalStudentsLimitMin;
    Double avgGpaLimitMax;
    Double avgGpaLimitMin;
}
