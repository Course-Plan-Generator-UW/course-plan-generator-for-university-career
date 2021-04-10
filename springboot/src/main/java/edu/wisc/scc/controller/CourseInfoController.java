package edu.wisc.scc.controller;


import com.alibaba.fastjson.JSONArray;
import common.JavaUtils;
import common.IdDto;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.wisc.scc.controller.dto.CourseInfoQueryDto;
import edu.wisc.scc.entity.CourseInfo;
import edu.wisc.scc.service.CourseInfoService;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import java.util.*;
import javax.annotation.Resource;

/**
 * course info(CourseInfo)表控制层
 *
 * @author Gao Qi
 * @since 2021-03-25 20:06:18
 */
@RestController
@RequestMapping("courseInfo")
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseInfoController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private CourseInfoService courseInfoService;

    /**
     * 分页查询所有数据
     *
     * @param page       分页对象
     * @param courseInfo 查询实体
     * @return 所有数据
     */
    //@GetMapping("queryPage.do")
    public R selectAll(Page<CourseInfo> page, CourseInfoQueryDto courseInfo) {
        log.info("selectAll params: page={} courseInfo={}",JSONObject.toJSONString(page),JSONObject.toJSONString(courseInfo));
        QueryWrapper<CourseInfo> query = new QueryWrapper<>();
        if (!JavaUtils.isEmpty(courseInfo.getId())) {
            query = query.eq("id",courseInfo.getId());
        }
        if (!JavaUtils.isEmpty(courseInfo.getStatus())) {
            query = query.eq("status",courseInfo.getStatus());
        }
        if (!JavaUtils.isEmpty(courseInfo.getCourseName())) {
            query = query.like("course_name",courseInfo.getCourseName());
        }
        if (!JavaUtils.isEmpty(courseInfo.getSubject())) {
            query = query.eq("subject",courseInfo.getSubject());
        }
        if (!JavaUtils.isEmpty(courseInfo.getSubjectArr())) {
            query = query.in("subject",courseInfo.getSubjectArr());
        }
        if (!JavaUtils.isEmpty(courseInfo.getTotalStudentsLimitMax())) {
            query = query.le("total_students",courseInfo.getTotalStudentsLimitMax());
        }
        if (!JavaUtils.isEmpty(courseInfo.getTotalStudentsLimitMin())) {
            query = query.ge("total_students",courseInfo.getTotalStudentsLimitMin());
        }
        if (!JavaUtils.isEmpty(courseInfo.getTotalStudents())) {
            query = query.ge("total_students",courseInfo.getTotalStudents());
        }
        if (!JavaUtils.isEmpty(courseInfo.getAvgGpaLimitMax())) {
            query = query.le("avg_gpa",courseInfo.getAvgGpaLimitMax());
        }
        if (!JavaUtils.isEmpty(courseInfo.getAvgGpaLimitMin())) {
            query = query.ge("avg_gpa",courseInfo.getAvgGpaLimitMin());
        }
        if (!JavaUtils.isEmpty(courseInfo.getAvgGpa())) {
            query = query.ge("avg_gpa",courseInfo.getAvgGpa());
        }
        return success(this.courseInfoService.page(page,query));
    }


    @GetMapping("queryPage.do")
    public R queryAll(CourseInfoQueryDto courseInfo) {
        log.info("selectAll params: courseInfo={}",JSONObject.toJSONString(courseInfo));
        QueryWrapper<CourseInfo> query = new QueryWrapper<>();
        if (!JavaUtils.isEmpty(courseInfo.getId())) {
            query = query.eq("id",courseInfo.getId());
        }
        if (!JavaUtils.isEmpty(courseInfo.getStatus())) {
            query = query.eq("status",courseInfo.getStatus());
        }
        if (!JavaUtils.isEmpty(courseInfo.getCourseName())) {
            query = query.like("course_name",courseInfo.getCourseName());
        }
        if (!JavaUtils.isEmpty(courseInfo.getSubject())) {
            query = query.eq("subject",courseInfo.getSubject());
        }
        if (!JavaUtils.isEmpty(courseInfo.getSubjectArr())) {
            query = query.in("subject",courseInfo.getSubjectArr());
        }
        if (!JavaUtils.isEmpty(courseInfo.getTotalStudentsLimitMax())) {
            query = query.le("total_students",courseInfo.getTotalStudentsLimitMax());
        }
        if (!JavaUtils.isEmpty(courseInfo.getTotalStudentsLimitMin())) {
            query = query.ge("total_students",courseInfo.getTotalStudentsLimitMin());
        }
        if (!JavaUtils.isEmpty(courseInfo.getTotalStudents())) {
            query = query.ge("total_students",courseInfo.getTotalStudents());
        }
        if (!JavaUtils.isEmpty(courseInfo.getAvgGpaLimitMax())) {
            query = query.le("avg_gpa",courseInfo.getAvgGpaLimitMax());
        }
        if (!JavaUtils.isEmpty(courseInfo.getAvgGpaLimitMin())) {
            query = query.ge("avg_gpa",courseInfo.getAvgGpaLimitMin());
        }
        if (!JavaUtils.isEmpty(courseInfo.getAvgGpa())) {
            query = query.ge("avg_gpa",courseInfo.getAvgGpa());
        }
        JSONObject obj = new JSONObject();
        obj.put("records", JSONArray.toJSON(this.courseInfoService.list(query)));
        return R.ok(obj);
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
        return success(this.courseInfoService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param courseInfo 实体对象
     * @return 新增结果
     */
    @PostMapping("insert.do")
    public R insert(@RequestBody CourseInfo courseInfo) {
        courseInfo.preInsert();
        boolean result = this.courseInfoService.save(courseInfo);
        if (result) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", courseInfo.getId());
            return success(jsonObject);
        } else {
            return failed("执行失败");
        }
    }

    /**
     * 修改数据
     *
     * @param courseInfo 实体对象
     * @return 修改结果
     */
    @PostMapping("updateById.do")
    public R patch(@RequestBody CourseInfo courseInfo) {
        String id = courseInfo.getId();
        if (JavaUtils.isEmpty(id)) {
            return R.failed("id cannot be empty.");
        }
        courseInfo.preUpdate();
        return success(this.courseInfoService.updateById(courseInfo));
    }

    /**
     * 批量修改数据
     *
     * @param courseInfoList 实体对象
     * @return 修改结果
     */
    @PostMapping("updateBatchById.do")
    public R patch(@RequestBody ArrayList<CourseInfo> courseInfoList) {
        for (CourseInfo courseInfo : courseInfoList) {
            String id = courseInfo.getId();
            if (JavaUtils.isEmpty(id)) {
                return R.failed("id cannot be empty.");
            }
            courseInfo.preUpdate();
        }
        return success(this.courseInfoService.updateBatchById(courseInfoList));
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
        return success(this.courseInfoService.removeById(id));
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
    /**
     * 获取所有subject不重名  按名字排序
     * @return
     */
    @GetMapping("queryAllSubject.do")
    public R delete2() {
        List<String> allSubject =  this.courseInfoService.queryAllSubject();
        return R.ok(allSubject);
    }


}
