package edu.wisc.scc.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.wisc.scc.dao.GradeDistributionDao;
import edu.wisc.scc.entity.GradeDistribution;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * (GradeDistribution)表服务实现类
 *
 * @author Gao Qi
 * @since 2021-03-24 06:38:49
 */
@Service("gradeDistributionService")
@Slf4j
public class GradeDistributionService extends ServiceImpl<GradeDistributionDao, GradeDistribution> {

}
