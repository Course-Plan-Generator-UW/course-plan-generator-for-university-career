package edu.wisc.scc.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.wisc.scc.dao.StudentChooseCourseRecordDao;
import edu.wisc.scc.entity.StudentChooseCourseRecord;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * student choose course record(StudentChooseCourseRecord)表服务实现类
 *
 * @author 蔺春华
 * @since 2021-04-07 21:06:47
 */
@Service("studentChooseCourseRecordService")
@Slf4j
public class StudentChooseCourseRecordService extends ServiceImpl<StudentChooseCourseRecordDao, StudentChooseCourseRecord> {

}
