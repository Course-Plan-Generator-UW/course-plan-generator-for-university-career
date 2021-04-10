package edu.wisc.scc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.wisc.scc.entity.SubjectInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * subject info(SubjectInfo)表数据库访问层
 *
 * @author 蔺春华
 * @since 2021-03-28 11:34:51
 */
@Mapper
public interface SubjectInfoDao extends BaseMapper<SubjectInfo> {

}
