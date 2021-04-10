package edu.wisc.scc.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.wisc.scc.dao.CourseStudentTagDao;
import edu.wisc.scc.entity.CourseStudentTag;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * t_course_student_tag(CourseStudentTag)表服务实现类
 *
 * @author 蔺春华
 * @since 2021-04-10 00:21:54
 */
@Service("courseStudentTagService")
@Slf4j
public class CourseStudentTagService extends ServiceImpl<CourseStudentTagDao, CourseStudentTag> {

}
