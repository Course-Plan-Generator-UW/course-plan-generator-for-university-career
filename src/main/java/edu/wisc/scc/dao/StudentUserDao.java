package edu.wisc.scc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.wisc.scc.entity.StudentUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * student user(StudentUser)表数据库访问层
 *
 * @author 高祈
 * @since 2021-03-17 23:15:15
 */
@Mapper
public interface StudentUserDao extends BaseMapper<StudentUser> {

}
