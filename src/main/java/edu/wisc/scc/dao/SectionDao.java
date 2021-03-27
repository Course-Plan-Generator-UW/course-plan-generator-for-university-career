package edu.wisc.scc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.wisc.scc.entity.Section;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Section)表数据库访问层
 *
 * @author Gao Qi
 * @since 2021-03-24 06:53:10
 */
@Mapper
public interface SectionDao extends BaseMapper<Section> {

}
