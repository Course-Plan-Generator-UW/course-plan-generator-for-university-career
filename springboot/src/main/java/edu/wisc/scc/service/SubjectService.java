package edu.wisc.scc.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.wisc.scc.dao.SubjectDao;
import edu.wisc.scc.entity.Subject;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * (Subject)表服务实现类
 *
 * @author Gao Qi
 * @since 2021-03-24 00:32:26
 */
@Service("subjectService")
@Slf4j
public class SubjectService extends ServiceImpl<SubjectDao, Subject> {

}
