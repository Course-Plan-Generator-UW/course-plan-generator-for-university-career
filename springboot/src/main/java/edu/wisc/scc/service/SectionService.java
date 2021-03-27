package edu.wisc.scc.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.wisc.scc.dao.SectionDao;
import edu.wisc.scc.entity.Section;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * (Section)表服务实现类
 *
 * @author Gao Qi
 * @since 2021-03-24 06:53:10
 */
@Service("sectionService")
@Slf4j
public class SectionService extends ServiceImpl<SectionDao, Section> {

}
