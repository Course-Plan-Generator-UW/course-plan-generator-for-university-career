package edu.wisc.scc.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.wisc.scc.dao.InstructorDao;
import edu.wisc.scc.entity.Instructor;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * (Instructor)表服务实现类
 *
 * @author Gao Qi
 * @since 2021-03-24 07:19:10
 */
@Service("instructorService")
@Slf4j
public class InstructorService extends ServiceImpl<InstructorDao, Instructor> {

}
