package edu.wisc.scc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.wisc.scc.entity.StudentChooseCourseRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * student choose course record(StudentChooseCourseRecord)表数据库访问层
 *
 * @author 蔺春华
 * @since 2021-04-07 21:06:47
 */
@Mapper
public interface StudentChooseCourseRecordDao extends BaseMapper<StudentChooseCourseRecord> {

}
