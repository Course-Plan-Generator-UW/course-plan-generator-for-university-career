package edu.wisc.scc.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.wisc.scc.dao.ScheduleDao;
import edu.wisc.scc.entity.Schedule;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * (Schedule)表服务实现类
 *
 * @author Gao Qi
 * @since 2021-03-24 06:43:54
 */
@Service("scheduleService")
@Slf4j
public class ScheduleService extends ServiceImpl<ScheduleDao, Schedule> {

}
