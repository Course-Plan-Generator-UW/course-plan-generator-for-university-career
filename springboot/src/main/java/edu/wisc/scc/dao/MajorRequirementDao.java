package edu.wisc.scc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.wisc.scc.entity.MajorRequirement;
import org.apache.ibatis.annotations.Mapper;

/**
 * major requirement(MajorRequirement)表数据库访问层
 *
 * @author 蔺春华
 * @since 2021-04-07 20:10:57
 */
@Mapper
public interface MajorRequirementDao extends BaseMapper<MajorRequirement> {

}
