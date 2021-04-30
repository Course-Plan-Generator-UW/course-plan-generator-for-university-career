package edu.wisc.scc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.wisc.scc.entity.CourseStudentTag;
import org.apache.ibatis.annotations.Mapper;

/**
 * t_course_student_tag(CourseStudentTag)表数据库访问层
 *
 * @author 蔺春华
 * @since 2021-04-10 00:21:54
 */
@Mapper
public interface CourseStudentTagDao extends BaseMapper<CourseStudentTag> {

}
