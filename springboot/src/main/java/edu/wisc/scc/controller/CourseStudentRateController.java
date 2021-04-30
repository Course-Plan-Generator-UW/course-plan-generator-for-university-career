package edu.wisc.scc.controller;


import common.JavaUtils;
import common.IdDto;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.wisc.scc.entity.CourseStudentRate;
import edu.wisc.scc.service.CourseStudentRateService;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import javax.annotation.Resource;

/**
 *
 * queryByCourseNameAndStudentId.do?courseName=xxx&studentId=xxx
 *
 *
 * t_course_student_rate(CourseStudentRate)表控制层
 *
 * @author 蔺春华
 * @since 2021-04-10 00:21:34
 */
@RestController
@RequestMapping("courseStudentRate")
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseStudentRateController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private CourseStudentRateService courseStudentRateService;

    /**
     * 分页查询所有数据
     *
     * @param page              分页对象
     * @param courseStudentRate 查询实体
     * @return 所有数据
     */
    @GetMapping("queryPage.do")
    public R selectAll(Page<CourseStudentRate> page, CourseStudentRate courseStudentRate) {
        if (JavaUtils.isEmpty(courseStudentRate.getId())) {
            courseStudentRate.setId(null);
        }
        if (JavaUtils.isEmpty(courseStudentRate.getStatus())) {
            courseStudentRate.setStatus(null);
        }
        if (JavaUtils.isEmpty(courseStudentRate.getCreateTime())) {
            courseStudentRate.setCreateTime(null);
        }
        if (JavaUtils.isEmpty(courseStudentRate.getUpdateTime())) {
            courseStudentRate.setUpdateTime(null);
        }
        if (JavaUtils.isEmpty(courseStudentRate.getCourseName())) {
            courseStudentRate.setCourseName(null);
        }
        if (JavaUtils.isEmpty(courseStudentRate.getStudentId())) {
            courseStudentRate.setStudentId(null);
        }
        if (JavaUtils.isEmpty(courseStudentRate.getRate())) {
            courseStudentRate.setRate(null);
        }
        return success(this.courseStudentRateService.page(page, new QueryWrapper<>(courseStudentRate)));
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
        return success(this.courseStudentRateService.getById(id));
    }

    /**
     * 通过主键查询单条数据
     *    courseName;
     *    String studentId;
     * @return 单条数据
     */
    @GetMapping("queryByCourseNameAndStudentId.do")
    public R selectOneBy(CourseStudentRate obj) {
        CourseStudentRate one = this.courseStudentRateService.getOne(new QueryWrapper<>(obj));
        if(one!=null){
           double rate = one.getRate();
            return success(rate);
        }else{
            return R.ok(null);
        }

    }

    /**
     * 新增数据
     *
     * @param courseStudentRate 实体对象
     * @return 新增结果
     */
    @PostMapping("insert.do")
    public R insert(@RequestBody CourseStudentRate courseStudentRate) {
        courseStudentRate.preInsert();
        boolean result = this.courseStudentRateService.save(courseStudentRate);
        if (result) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", courseStudentRate.getId());
            this.courseStudentRateService.updateCourseAvgRate(courseStudentRate.getCourseName());
            return success(jsonObject);
        } else {
            return failed("执行失败");
        }
    }

    /**
     * 修改数据
     *
     * @param courseStudentRate 实体对象
     * @return 修改结果
     */
    @PostMapping("updateById.do")
    public R patch(@RequestBody CourseStudentRate courseStudentRate) {
        String id = courseStudentRate.getId();
        if (JavaUtils.isEmpty(id)) {
            return R.failed("id cannot be empty.");
        }
        courseStudentRate.preUpdate();
        R r = success(this.courseStudentRateService.updateById(courseStudentRate));
        this.courseStudentRateService.updateCourseAvgRate(courseStudentRate.getCourseName());
        return r;
    }

    /**
     * 批量修改数据
     *
     * @param courseStudentRateList 实体对象
     * @return 修改结果
     */
    @PostMapping("updateBatchById.do")
    public R patch(@RequestBody ArrayList<CourseStudentRate> courseStudentRateList) {
        for (CourseStudentRate courseStudentRate : courseStudentRateList) {
            String id = courseStudentRate.getId();
            if (JavaUtils.isEmpty(id)) {
                return R.failed("id cannot be empty.");
            }
            courseStudentRate.preUpdate();
        }
        return success(this.courseStudentRateService.updateBatchById(courseStudentRateList));
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
        return success(this.courseStudentRateService.removeById(id));
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
