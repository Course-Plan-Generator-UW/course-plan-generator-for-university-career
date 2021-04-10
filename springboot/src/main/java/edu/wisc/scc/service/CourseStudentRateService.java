package edu.wisc.scc.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.wisc.scc.dao.CourseStudentRateDao;
import edu.wisc.scc.entity.CourseStudentRate;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * t_course_student_rate(CourseStudentRate)表服务实现类
 *
 * @author 蔺春华
 * @since 2021-04-10 00:21:34
 */
@Service("courseStudentRateService")
@Slf4j
public class CourseStudentRateService extends ServiceImpl<CourseStudentRateDao, CourseStudentRate> {

    public int updateCourseAvgRate(String courseName) {
        return this.baseMapper.updateCourseAvgRate(courseName);
    }
}
