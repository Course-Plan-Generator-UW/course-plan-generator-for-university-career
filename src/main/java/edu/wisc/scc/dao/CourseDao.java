package edu.wisc.scc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.wisc.scc.entity.Course;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Course)表数据库访问层
 *
 * @author Gao Qi
 * @since 2021-03-24 06:23:16
 */
@Mapper
public interface CourseDao extends BaseMapper<Course> {

}
