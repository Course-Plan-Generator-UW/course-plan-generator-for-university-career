package edu.wisc.scc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.wisc.scc.entity.GradeDistribution;
import org.apache.ibatis.annotations.Mapper;

/**
 * (GradeDistribution)表数据库访问层
 *
 * @author Gao Qi
 * @since 2021-03-24 06:38:49
 */
@Mapper
public interface GradeDistributionDao extends BaseMapper<GradeDistribution> {

}
