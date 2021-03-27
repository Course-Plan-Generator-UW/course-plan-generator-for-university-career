package edu.wisc.scc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.wisc.scc.entity.Subject;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Subject)表数据库访问层
 *
 * @author Gao Qi
 * @since 2021-03-24 00:32:26
 */
@Mapper
public interface SubjectDao extends BaseMapper<Subject> {

}
