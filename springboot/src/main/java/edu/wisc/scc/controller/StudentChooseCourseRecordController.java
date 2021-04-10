package edu.wisc.scc.controller;


import common.JavaUtils;
import common.IdDto;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.wisc.scc.entity.StudentChooseCourseRecord;
import edu.wisc.scc.service.StudentChooseCourseRecordService;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * student choose course record(StudentChooseCourseRecord)表控制层
 *
 * @author 蔺春华
 * @since 2021-04-07 21:06:48
 */
@RestController
@RequestMapping("studentChooseCourseRecord")
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
public class StudentChooseCourseRecordController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private StudentChooseCourseRecordService studentChooseCourseRecordService;

    /**
     * 分页查询所有数据
     *
     * @param page                      分页对象
     * @param studentChooseCourseRecord 查询实体
     * @return 所有数据
     */
    @GetMapping("queryPage.do")
    public R selectAll(Page<StudentChooseCourseRecord> page, StudentChooseCourseRecord studentChooseCourseRecord) {
        if (JavaUtils.isEmpty(studentChooseCourseRecord.getId())) {
            studentChooseCourseRecord.setId(null);
        }
        if (JavaUtils.isEmpty(studentChooseCourseRecord.getStatus())) {
            studentChooseCourseRecord.setStatus(null);
        }
        if (JavaUtils.isEmpty(studentChooseCourseRecord.getCreateTime())) {
            studentChooseCourseRecord.setCreateTime(null);
        }
        if (JavaUtils.isEmpty(studentChooseCourseRecord.getUpdateTime())) {
            studentChooseCourseRecord.setUpdateTime(null);
        }
        if (JavaUtils.isEmpty(studentChooseCourseRecord.getStudentId())) {
            studentChooseCourseRecord.setStudentId(null);
        }
        if (JavaUtils.isEmpty(studentChooseCourseRecord.getCourseName())) {
            studentChooseCourseRecord.setCourseName(null);
        }
        if (JavaUtils.isEmpty(studentChooseCourseRecord.getWhichYear())) {
            studentChooseCourseRecord.setWhichYear(null);
        }
        if (JavaUtils.isEmpty(studentChooseCourseRecord.getSemester())) {
            studentChooseCourseRecord.setSemester(null);
        }
        return success(this.studentChooseCourseRecordService.page(page, new QueryWrapper<>(studentChooseCourseRecord)));
    }

    // queryMyCourses.do?userId=xxxx
    @GetMapping("queryMyCourses.do")
    public R selectAll(HttpServletRequest request) {
        String userIdInToken = (String)request.getAttribute("userId");
        if(userIdInToken==null){
            userIdInToken = request.getParameter("userId");
        }
        return success(this.studentChooseCourseRecordService.list(
                new QueryWrapper<StudentChooseCourseRecord>().eq("student_id",userIdInToken).orderByAsc("which_year","semester"))
        );
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
        return success(this.studentChooseCourseRecordService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param studentChooseCourseRecord 实体对象
     * @return 新增结果
     */
    @PostMapping("addCourse.do")
    public R insert(@RequestBody StudentChooseCourseRecord studentChooseCourseRecord, HttpServletRequest request) {
        String userIdInToken = (String)request.getAttribute("userId");
        if(userIdInToken==null){
            //return R.failed("get userId fail");
            userIdInToken = studentChooseCourseRecord.getStudentId();
        }
        studentChooseCourseRecord.setStudentId(userIdInToken);
        StudentChooseCourseRecord one = this.studentChooseCourseRecordService
                .getOne(new QueryWrapper<StudentChooseCourseRecord>(studentChooseCourseRecord));
        if(one!=null){
            return R.failed("exists");
        }
        studentChooseCourseRecord.preInsert();
        boolean result = this.studentChooseCourseRecordService.save(studentChooseCourseRecord);
        if (result) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", studentChooseCourseRecord.getId());
            return success(jsonObject);
        } else {
            return failed("执行失败");
        }
    }

    /**
     * 修改数据
     *
     * @param studentChooseCourseRecord 实体对象
     * @return 修改结果
     */
    @PostMapping("updateById.do")
    public R patch(@RequestBody StudentChooseCourseRecord studentChooseCourseRecord) {
        String id = studentChooseCourseRecord.getId();
        if (JavaUtils.isEmpty(id)) {
            return R.failed("id cannot be empty.");
        }
        studentChooseCourseRecord.preUpdate();
        return success(this.studentChooseCourseRecordService.updateById(studentChooseCourseRecord));
    }

    /**
     * 批量修改数据
     *
     * @param studentChooseCourseRecordList 实体对象
     * @return 修改结果
     */
    @PostMapping("updateBatchById.do")
    public R patch(@RequestBody ArrayList<StudentChooseCourseRecord> studentChooseCourseRecordList) {
        for (StudentChooseCourseRecord studentChooseCourseRecord : studentChooseCourseRecordList) {
            String id = studentChooseCourseRecord.getId();
            if (JavaUtils.isEmpty(id)) {
                return R.failed("id cannot be empty.");
            }
            studentChooseCourseRecord.preUpdate();
        }
        return success(this.studentChooseCourseRecordService.updateBatchById(studentChooseCourseRecordList));
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
        return success(this.studentChooseCourseRecordService.removeById(id));
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
