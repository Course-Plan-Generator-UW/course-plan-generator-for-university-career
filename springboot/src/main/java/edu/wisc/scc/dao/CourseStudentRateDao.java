package edu.wisc.scc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.wisc.scc.entity.CourseStudentRate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * t_course_student_rate(CourseStudentRate)表数据库访问层
 * @author 蔺春华
 * @since 2021-04-10 00:21:34
 */
@Mapper
public interface CourseStudentRateDao extends BaseMapper<CourseStudentRate> {

@Update("update t_course_info " +
        " set avg_rate = ( select avg(rate) from t_course_student_rate " +
                            "where course_name = #{courseName} and rate is not null ) " +
        " where course_name = #{courseName}")
    int updateCourseAvgRate(String courseName);
}
