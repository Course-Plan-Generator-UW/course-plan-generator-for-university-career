package edu.wisc.scc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.wisc.scc.entity.CourseInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * course info(CourseInfo)表数据库访问层
 * @author Gao Qi
 * @since 2021-03-25 20:06:17
 */
@Mapper
public interface CourseInfoDao extends BaseMapper<CourseInfo> {
    @Select("select DISTINCT subject from t_course_info order by subject asc")
    List<String> queryAllSubject();
}
