package edu.wisc.scc.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.wisc.scc.dao.StudentUserDao;
import edu.wisc.scc.entity.StudentUser;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * student user(StudentUser)表服务实现类
 *
 * @author 高祈
 * @since 2021-03-17 23:15:15
 */
@Service("studentUserService")
@Slf4j
public class StudentUserService extends ServiceImpl<StudentUserDao, StudentUser> {

}
