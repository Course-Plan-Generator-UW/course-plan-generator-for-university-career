package edu.wisc.scc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.wisc.scc.entity.Instructor;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Instructor)表数据库访问层
 *
 * @author Gao Qi
 * @since 2021-03-24 07:19:09
 */
@Mapper
public interface InstructorDao extends BaseMapper<Instructor> {

}
