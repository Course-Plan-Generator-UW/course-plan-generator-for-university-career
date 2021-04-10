package edu.wisc.scc.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.wisc.scc.dao.MajorRequirementDao;
import edu.wisc.scc.entity.MajorRequirement;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * major requirement(MajorRequirement)表服务实现类
 *
 * @author 蔺春华
 * @since 2021-04-07 20:10:57
 */
@Service("majorRequirementService")
@Slf4j
public class MajorRequirementService extends ServiceImpl<MajorRequirementDao, MajorRequirement> {

}
