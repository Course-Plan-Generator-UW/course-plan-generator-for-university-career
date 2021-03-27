package edu.wisc.scc.controller;


import common.JavaUtils;
import common.IdDto;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.wisc.scc.entity.StudentUser;
import edu.wisc.scc.service.StudentUserService;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import javax.annotation.Resource;

/**
 * student user(StudentUser)表控制层
 *
 * @author 高祈
 * @since 2021-03-17 23:15:15
 */
@RestController
@RequestMapping("studentUser")
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
public class StudentUserController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private StudentUserService studentUserService;

    /**
     * 分页查询所有数据
     *
     * @param page        分页对象
     * @param studentUser 查询实体
     * @return 所有数据
     */
    @GetMapping("queryPage.do")
    public R selectAll(Page<StudentUser> page, StudentUser studentUser) {
        if (JavaUtils.isEmpty(studentUser.getId())) {
            studentUser.setId(null);
        }
        if (JavaUtils.isEmpty(studentUser.getStatus())) {
            studentUser.setStatus(null);
        }
        if (JavaUtils.isEmpty(studentUser.getCreateTime())) {
            studentUser.setCreateTime(null);
        }
        if (JavaUtils.isEmpty(studentUser.getUpdateTime())) {
            studentUser.setUpdateTime(null);
        }
        if (JavaUtils.isEmpty(studentUser.getUserName())) {
            studentUser.setUserName(null);
        }
        if (JavaUtils.isEmpty(studentUser.getUserPwd())) {
            studentUser.setUserPwd(null);
        }
        if (JavaUtils.isEmpty(studentUser.getStudentIdentity())) {
            studentUser.setStudentIdentity(null);
        }
        if (JavaUtils.isEmpty(studentUser.getEmail())) {
            studentUser.setEmail(null);
        }
        if (JavaUtils.isEmpty(studentUser.getFirstName())) {
            studentUser.setFirstName(null);
        }
        if (JavaUtils.isEmpty(studentUser.getLastName())) {
            studentUser.setLastName(null);
        }
        return success(this.studentUserService.page(page, new QueryWrapper<>(studentUser)));
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
        return success(this.studentUserService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param studentUser 实体对象
     * @return 新增结果
     */
//    @PostMapping("insert.do")
//    public R insert(@RequestBody StudentUser studentUser) {
//        studentUser.preInsert();
//        boolean result = this.studentUserService.save(studentUser);
//        if (result) {
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("id", studentUser.getId());
//            return success(jsonObject);
//        } else {
//            return failed("执行失败");
//        }
//    }

    /**
     * 修改数据
     *
     * @param studentUser 实体对象
     * @return 修改结果
     */
    @PostMapping("updateById.do")
    public R patch(@RequestBody StudentUser studentUser) {
        String id = studentUser.getId();
        if (JavaUtils.isEmpty(id)) {
            return R.failed("id cannot be empty.");
        }
        studentUser.preUpdate();
        return success(this.studentUserService.updateById(studentUser));
    }

    /**
     * 批量修改数据
     *
     * @param studentUserList 实体对象
     * @return 修改结果
     */
    @PostMapping("updateBatchById.do")
    public R patch(@RequestBody ArrayList<StudentUser> studentUserList) {
        for (StudentUser studentUser : studentUserList) {
            String id = studentUser.getId();
            if (JavaUtils.isEmpty(id)) {
                return R.failed("id cannot be empty.");
            }
            studentUser.preUpdate();
        }
        return success(this.studentUserService.updateBatchById(studentUserList));
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
        return success(this.studentUserService.removeById(id));
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
