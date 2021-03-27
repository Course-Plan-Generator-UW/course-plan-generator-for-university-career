package edu.wisc.scc.security;

import edu.wisc.scc.entity.StudentUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserLoginDao {
    @Select("select * from t_student_user " +
            "where user_name=#{username} " +
            "and user_pwd=#{password}")
    StudentUser getOneByUsernameAndPassword(
            @Param("username") String username,
            @Param("password")String password);


}
