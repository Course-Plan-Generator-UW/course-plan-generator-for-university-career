package edu.wisc.scc.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.wisc.scc.dao.CourseInfoDao;
import edu.wisc.scc.entity.CourseInfo;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * course info(CourseInfo)表服务实现类
 *
 * @author `Gao Qi`
 * @since 2021-03-25 20:06:17
 */
@Service("courseInfoService")
@Slf4j
public class CourseInfoService extends ServiceImpl<CourseInfoDao, CourseInfo> {

    public List<String> queryAllSubject() {
        return this.baseMapper.queryAllSubject();
    }
}
