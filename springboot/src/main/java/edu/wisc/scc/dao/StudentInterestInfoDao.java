package edu.wisc.scc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.wisc.scc.entity.StudentInterestInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * interest info(StudentInterestInfo)表数据库访问层
 *
 * @author 蔺春华
 * @since 2021-03-28 11:35:11
 */
@Mapper
public interface StudentInterestInfoDao extends BaseMapper<StudentInterestInfo> {

    @Select("select * from t_student_interest_info where student_id=#{userId} and interest_level >=  #{minLevel} order by interest_level desc")
    List<StudentInterestInfo> searchInterestSubjectLevelGe(String userId, Integer minLevel);

    @Insert("" +
            "insert into t_student_interest_info(subject_name,student_id,id,create_time,update_time,status) " +
            "select subject_name,student_id,md5(CONCAT(subject_name,student_id)) id,SYSDATE(),SYSDATE(),1 " +
            " from (select DISTINCT subject subject_name from t_course_info order by subject asc " +
            ") t1,(select #{studentId} student_id from t_student_user) t2")
    int initInterestsByStudentId(String studentId);
}
