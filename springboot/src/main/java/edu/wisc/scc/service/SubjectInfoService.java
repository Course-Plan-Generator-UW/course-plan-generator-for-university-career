package edu.wisc.scc.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.wisc.scc.dao.SubjectInfoDao;
import edu.wisc.scc.entity.SubjectInfo;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * subject info(SubjectInfo)表服务实现类
 *
 * @author 蔺春华
 * @since 2021-03-28 11:34:51
 */
@Service("subjectInfoService")
@Slf4j
public class SubjectInfoService extends ServiceImpl<SubjectInfoDao, SubjectInfo> {

}
