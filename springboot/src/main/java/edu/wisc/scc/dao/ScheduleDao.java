package edu.wisc.scc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.wisc.scc.entity.Schedule;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Schedule)表数据库访问层
 *
 * @author Gao Qi
 * @since 2021-03-24 06:43:54
 */
@Mapper
public interface ScheduleDao extends BaseMapper<Schedule> {

}
