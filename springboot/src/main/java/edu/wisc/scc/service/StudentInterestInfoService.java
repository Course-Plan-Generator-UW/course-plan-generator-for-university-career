package edu.wisc.scc.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.wisc.scc.dao.StudentInterestInfoDao;
import edu.wisc.scc.entity.StudentInterestInfo;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * interest info(StudentInterestInfo)表服务实现类
 *
 * @author 蔺春华
 * @since 2021-03-28 11:35:11
 */
@Service("studentInterestInfoService")
@Slf4j
public class StudentInterestInfoService extends ServiceImpl<StudentInterestInfoDao, StudentInterestInfo> {

    public List<StudentInterestInfo> searchInterestSubjectLevelGe(String userId, Integer topN) {
        return this.baseMapper.searchInterestSubjectLevelGe(userId,topN);
    }

    public int initInterestsByStudentId(String studentId) {
        return this.baseMapper.initInterestsByStudentId(studentId);
    }
}
