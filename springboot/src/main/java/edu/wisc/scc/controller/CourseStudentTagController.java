package edu.wisc.scc.controller;


import common.JavaUtils;
import common.IdDto;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.wisc.scc.entity.CourseStudentTag;
import edu.wisc.scc.service.CourseStudentTagService;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import javax.annotation.Resource;

/**
 * t_course_student_tag(CourseStudentTag)表控制层
 *
 * insert.do
 * queryAll.do
 * deleteById.do
 *
 * @author 蔺春华
 * @since 2021-04-10 00:21:54
 */
@RestController
@RequestMapping("courseStudentTag")
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseStudentTagController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private CourseStudentTagService courseStudentTagService;

    /**
     * 分页查询所有数据
     *
     * @param page             分页对象
     * @param courseStudentTag 查询实体
     * @return 所有数据
     */
    @GetMapping("queryPage.do")
    public R selectAll(Page<CourseStudentTag> page, CourseStudentTag courseStudentTag) {
        if (JavaUtils.isEmpty(courseStudentTag.getId())) {
            courseStudentTag.setId(null);
        }
        if (JavaUtils.isEmpty(courseStudentTag.getStatus())) {
            courseStudentTag.setStatus(null);
        }
        if (JavaUtils.isEmpty(courseStudentTag.getCreateTime())) {
            courseStudentTag.setCreateTime(null);
        }
        if (JavaUtils.isEmpty(courseStudentTag.getUpdateTime())) {
            courseStudentTag.setUpdateTime(null);
        }
        if (JavaUtils.isEmpty(courseStudentTag.getCourseName())) {
            courseStudentTag.setCourseName(null);
        }
        if (JavaUtils.isEmpty(courseStudentTag.getStudentId())) {
            courseStudentTag.setStudentId(null);
        }
        if (JavaUtils.isEmpty(courseStudentTag.getTag())) {
            courseStudentTag.setTag(null);
        }
        return success(this.courseStudentTagService.page(page, new QueryWrapper<>(courseStudentTag)));
    }


    /**
     * 1.只给课程name 该课程的所有标签
     * 2.提供了课程name和学生id  得到我打的标签
     * @param courseStudentTag
     * @return
     */
    @GetMapping("queryAll.do")
    public R selectAll(CourseStudentTag courseStudentTag) {
        if (JavaUtils.isEmpty(courseStudentTag.getId())) {
            courseStudentTag.setId(null);
        }
        if (JavaUtils.isEmpty(courseStudentTag.getStatus())) {
            courseStudentTag.setStatus(null);
        }
        if (JavaUtils.isEmpty(courseStudentTag.getCreateTime())) {
            courseStudentTag.setCreateTime(null);
        }
        if (JavaUtils.isEmpty(courseStudentTag.getUpdateTime())) {
            courseStudentTag.setUpdateTime(null);
        }
        if (JavaUtils.isEmpty(courseStudentTag.getCourseName())) {
            courseStudentTag.setCourseName(null);
        }
        if (JavaUtils.isEmpty(courseStudentTag.getStudentId())) {
            courseStudentTag.setStudentId(null);
        }
        if (JavaUtils.isEmpty(courseStudentTag.getTag())) {
            courseStudentTag.setTag(null);
        }
        return success(this.courseStudentTagService.list(new QueryWrapper<>(courseStudentTag)));
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
        return success(this.courseStudentTagService.getById(id));
    }


    /**
     * 新增数据
     *
     * @param courseStudentTag 实体对象
     * @return 新增结果
     */
    @PostMapping("insert.do")
    public R insert(@RequestBody CourseStudentTag courseStudentTag) {
        courseStudentTag.preInsert();
        boolean result = this.courseStudentTagService.save(courseStudentTag);
        if (result) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", courseStudentTag.getId());
            return success(jsonObject);
        } else {
            return failed("执行失败");
        }
    }



    /**
     * 修改数据
     *
     * @param courseStudentTag 实体对象
     * @return 修改结果
     */
    @PostMapping("updateById.do")
    public R patch(@RequestBody CourseStudentTag courseStudentTag) {
        String id = courseStudentTag.getId();
        if (JavaUtils.isEmpty(id)) {
            return R.failed("id cannot be empty.");
        }
        courseStudentTag.preUpdate();
        return success(this.courseStudentTagService.updateById(courseStudentTag));
    }

    /**
     * 批量修改数据
     *
     * @param courseStudentTagList 实体对象
     * @return 修改结果
     */
    @PostMapping("updateBatchById.do")
    public R patch(@RequestBody ArrayList<CourseStudentTag> courseStudentTagList) {
        for (CourseStudentTag courseStudentTag : courseStudentTagList) {
            String id = courseStudentTag.getId();
            if (JavaUtils.isEmpty(id)) {
                return R.failed("id cannot be empty.");
            }
            courseStudentTag.preUpdate();
        }
        return success(this.courseStudentTagService.updateBatchById(courseStudentTagList));
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
        return success(this.courseStudentTagService.removeById(id));
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
