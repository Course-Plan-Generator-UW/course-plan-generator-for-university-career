package edu.wisc.scc.controller;


import common.JavaUtils;
import common.IdDto;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import edu.wisc.scc.entity.StudentInterestInfo;
import edu.wisc.scc.service.StudentInterestInfoService;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * interest info(StudentInterestInfo)表控制层
 *
 * @author 蔺春华
 * @since 2021-03-28 11:35:11
 */
@RestController
@RequestMapping("studentInterestInfo")
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
public class StudentInterestInfoController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private StudentInterestInfoService studentInterestInfoService;

    /**
     * 分页查询所有数据
     * @return 所有数据
     */
    @GetMapping("queryAllByUser.do")
    public R selectAllByUser(HttpServletRequest request) {
        StudentInterestInfo studentInterestInfo = new StudentInterestInfo();
        studentInterestInfo.setStudentId(request.getAttribute("userId").toString());
        if (JavaUtils.isEmpty(studentInterestInfo.getId())) {
            studentInterestInfo.setId(null);
        }
        if (JavaUtils.isEmpty(studentInterestInfo.getStatus())) {
            studentInterestInfo.setStatus(null);
        }
        if (JavaUtils.isEmpty(studentInterestInfo.getCreateTime())) {
            studentInterestInfo.setCreateTime(null);
        }
        if (JavaUtils.isEmpty(studentInterestInfo.getUpdateTime())) {
            studentInterestInfo.setUpdateTime(null);
        }
        if (JavaUtils.isEmpty(studentInterestInfo.getSubjectName())) {
            studentInterestInfo.setSubjectName(null);
        }
        if (JavaUtils.isEmpty(studentInterestInfo.getStudentId())) {
            studentInterestInfo.setStudentId(null);
        }
        if (JavaUtils.isEmpty(studentInterestInfo.getInterestLevel())) {
            studentInterestInfo.setInterestLevel(null);
        }
        return R.ok(
                this.studentInterestInfoService.list(new QueryWrapper<>(studentInterestInfo))
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
        return success(this.studentInterestInfoService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param studentInterestInfo 实体对象
     * @return 新增结果
     */
    @PostMapping("insert.do")
    public R insert(@RequestBody StudentInterestInfo studentInterestInfo) {
        studentInterestInfo.preInsert();
        boolean result = this.studentInterestInfoService.save(studentInterestInfo);
        if (result) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", studentInterestInfo.getId());
            return success(jsonObject);
        } else {
            return failed("执行失败");
        }
    }

    /**
     * 新增数据
     * @return 新增结果
     */
    @PostMapping("setLevel.do")
    public R setLevel(@RequestBody StudentInterestInfo studentInterestInfo, HttpServletRequest request) {
        String subjectId=studentInterestInfo.getSubjectName();
        String userId = request.getAttribute("userId").toString();
        StudentInterestInfo one = this.studentInterestInfoService.getOne(
                new QueryWrapper<StudentInterestInfo>()
                        .eq("subject_name", subjectId)
                        .eq("student_id", userId));
        if(one!=null) {
            this.studentInterestInfoService.removeById(one.getId());
        }
        studentInterestInfo.setStudentId(userId);
        this.insert(studentInterestInfo);
        return R.ok("执行成功");
    }

    /**
     * 修改数据
     *
     * @param studentInterestInfo 实体对象
     * @return 修改结果
     */
    @PostMapping("updateById.do")
    public R patch(@RequestBody StudentInterestInfo studentInterestInfo) {
        String id = studentInterestInfo.getId();
        if (JavaUtils.isEmpty(id)) {
            return R.failed("id cannot be empty.");
        }
        studentInterestInfo.preUpdate();
        return success(this.studentInterestInfoService.updateById(studentInterestInfo));
    }

    /**
     * 批量修改数据
     *
     * @param studentInterestInfoList 实体对象
     * @return 修改结果
     */
    @PostMapping("updateBatchById.do")
    public R patch(@RequestBody ArrayList<StudentInterestInfo> studentInterestInfoList) {
        for (StudentInterestInfo studentInterestInfo : studentInterestInfoList) {
            String id = studentInterestInfo.getId();
            if (JavaUtils.isEmpty(id)) {
                return R.failed("id cannot be empty.");
            }
            studentInterestInfo.preUpdate();
        }
        return success(this.studentInterestInfoService.updateBatchById(studentInterestInfoList));
    }

    /**
     *
     * @param request
     * @param level
     * @return
     */
    @GetMapping("getInterestSubjectWhenLevelGe.do")
    public R searchInterestSubjectLevelGe(HttpServletRequest request,Integer level){
        String userId = request.getAttribute("userId").toString();
        return R.ok(this.studentInterestInfoService.searchInterestSubjectLevelGe(userId,level));
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
        return success(this.studentInterestInfoService.removeById(id));
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
