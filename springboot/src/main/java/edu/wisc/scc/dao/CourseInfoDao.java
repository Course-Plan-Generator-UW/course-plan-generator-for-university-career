package edu.wisc.scc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.wisc.scc.entity.CourseInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * course info(CourseInfo)表数据库访问层
 *
 * @author Gao Qi
 * @since 2021-03-25 20:06:17
 */
@Mapper
public interface CourseInfoDao extends BaseMapper<CourseInfo> {

}
