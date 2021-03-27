package edu.wisc.scc.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.wisc.scc.dao.CourseDao;
import edu.wisc.scc.entity.Course;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * (Course)表服务实现类
 *
 * @author Gao Qi
 * @since 2021-03-24 06:23:16
 */
@Service("courseService")
@Slf4j
public class CourseService extends ServiceImpl<CourseDao, Course> {

}
