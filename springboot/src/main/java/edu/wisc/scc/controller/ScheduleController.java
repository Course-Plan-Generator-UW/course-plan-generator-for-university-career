package edu.wisc.scc.controller;


import common.JavaUtils;
import common.IdDto;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.wisc.scc.entity.Schedule;
import edu.wisc.scc.service.ScheduleService;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import javax.annotation.Resource;

/**
 * (Schedule)表控制层
 *
 * @author Gao Qi
 * @since 2021-03-24 06:43:54
 */
@RestController
@RequestMapping("schedule")
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
public class ScheduleController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private ScheduleService scheduleService;

    /**
     * 分页查询所有数据
     *
     * @param page     分页对象
     * @param schedule 查询实体
     * @return 所有数据
     */
    @GetMapping("queryPage.do")
    public R selectAll(Page<Schedule> page, Schedule schedule) {
        if (JavaUtils.isEmpty(schedule.getId())) {
            schedule.setId(null);
        }
        if (JavaUtils.isEmpty(schedule.getStartTime())) {
            schedule.setStartTime(null);
        }
        if (JavaUtils.isEmpty(schedule.getEndTime())) {
            schedule.setEndTime(null);
        }
        if (JavaUtils.isEmpty(schedule.getMon())) {
            schedule.setMon(null);
        }
        if (JavaUtils.isEmpty(schedule.getTues())) {
            schedule.setTues(null);
        }
        if (JavaUtils.isEmpty(schedule.getWed())) {
            schedule.setWed(null);
        }
        if (JavaUtils.isEmpty(schedule.getThurs())) {
            schedule.setThurs(null);
        }
        if (JavaUtils.isEmpty(schedule.getFri())) {
            schedule.setFri(null);
        }
        if (JavaUtils.isEmpty(schedule.getSat())) {
            schedule.setSat(null);
        }
        if (JavaUtils.isEmpty(schedule.getSun())) {
            schedule.setSun(null);
        }
        if (JavaUtils.isEmpty(schedule.getCreateTime())) {
            schedule.setCreateTime(null);
        }
        if (JavaUtils.isEmpty(schedule.getUpdateTime())) {
            schedule.setUpdateTime(null);
        }
        if (JavaUtils.isEmpty(schedule.getStatus())) {
            schedule.setStatus(null);
        }
        return success(this.scheduleService.page(page, new QueryWrapper<>(schedule)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("queryById.do")
    public R selectOne(String id) {
        if (JavaUtils.isEmpty(id)) {
            return R.failed("id cannot be empty.");
        }
        return success(this.scheduleService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param schedule 实体对象
     * @return 新增结果
     */
    @PostMapping("insert.do")
    public R insert(@RequestBody Schedule schedule) {
        schedule.preInsert();
        boolean result = this.scheduleService.save(schedule);
        if (result) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", schedule.getId());
            return success(jsonObject);
        } else {
            return failed("执行失败");
        }
    }

    /**
     * 修改数据
     *
     * @param schedule 实体对象
     * @return 修改结果
     */
    @PostMapping("updateById.do")
    public R patch(@RequestBody Schedule schedule) {
        String id = schedule.getId();
        if (JavaUtils.isEmpty(id)) {
            return R.failed("id cannot be empty.");
        }
        schedule.preUpdate();
        return success(this.scheduleService.updateById(schedule));
    }

    /**
     * 批量修改数据
     *
     * @param scheduleList 实体对象
     * @return 修改结果
     */
    @PostMapping("updateBatchById.do")
    public R patch(@RequestBody ArrayList<Schedule> scheduleList) {
        for (Schedule schedule : scheduleList) {
            String id = schedule.getId();
            if (JavaUtils.isEmpty(id)) {
                return R.failed("id cannot be empty.");
            }
            schedule.preUpdate();
        }
        return success(this.scheduleService.updateBatchById(scheduleList));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除结果
     */
    @GetMapping("deleteById.do")
    public R delete(String id) {
        if (JavaUtils.isEmpty(id)) {
            return R.failed("id cannot be empty.");
        }
        return success(this.scheduleService.removeById(id));
    }

    /**
     * 删除数据
     *
     * @param entity 包含主键的entity
     * @return 删除结果
     */
    @PostMapping("deleteById.do")
    public R delete2(IdDto entity) {
        String id = entity.getId();
        return this.delete(id);
    }
}
