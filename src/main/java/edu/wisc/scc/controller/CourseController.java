package edu.wisc.scc.controller;


import common.JavaUtils;
import common.IdDto;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.wisc.scc.entity.Course;
import edu.wisc.scc.service.CourseService;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import javax.annotation.Resource;

/**
 * (Course)表控制层
 *
 * @author Gao Qi
 * @since 2021-03-24 06:23:16
 */
@RestController
@RequestMapping("course")
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private CourseService courseService;

    /**
     * 分页查询所有数据
     *
     * @param page   分页对象
     * @param course 查询实体
     * @return 所有数据
     */
    @GetMapping("queryPage.do")
    public R selectAll(Page<Course> page, Course course) {
        if (JavaUtils.isEmpty(course.getId())) {
            course.setId(null);
        }
        if (JavaUtils.isEmpty(course.getName())) {
            course.setName(null);
        }
        if (JavaUtils.isEmpty(course.getNumber())) {
            course.setNumber(null);
        }
        if (JavaUtils.isEmpty(course.getCreateTime())) {
            course.setCreateTime(null);
        }
        if (JavaUtils.isEmpty(course.getUpdateTime())) {
            course.setUpdateTime(null);
        }
        if (JavaUtils.isEmpty(course.getStatus())) {
            course.setStatus(null);
        }
        return success(this.courseService.page(page, new QueryWrapper<>(course)));
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
        return success(this.courseService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param course 实体对象
     * @return 新增结果
     */
    @PostMapping("insert.do")
    public R insert(@RequestBody Course course) {
        course.preInsert();
        boolean result = this.courseService.save(course);
        if (result) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", course.getId());
            return success(jsonObject);
        } else {
            return failed("执行失败");
        }
    }

    /**
     * 修改数据
     *
     * @param course 实体对象
     * @return 修改结果
     */
    @PostMapping("updateById.do")
    public R patch(@RequestBody Course course) {
        String id = course.getId();
        if (JavaUtils.isEmpty(id)) {
            return R.failed("id cannot be empty.");
        }
        course.preUpdate();
        return success(this.courseService.updateById(course));
    }

    /**
     * 批量修改数据
     *
     * @param courseList 实体对象
     * @return 修改结果
     */
    @PostMapping("updateBatchById.do")
    public R patch(@RequestBody ArrayList<Course> courseList) {
        for (Course course : courseList) {
            String id = course.getId();
            if (JavaUtils.isEmpty(id)) {
                return R.failed("id cannot be empty.");
            }
            course.preUpdate();
        }
        return success(this.courseService.updateBatchById(courseList));
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
        return success(this.courseService.removeById(id));
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
