package edu.wisc.scc.controller;


import common.JavaUtils;
import common.IdDto;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.wisc.scc.entity.Subject;
import edu.wisc.scc.service.SubjectService;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import javax.annotation.Resource;

/**
 * (Subject)表控制层
 *
 * @author Gao Qi
 * @since 2021-03-24 00:32:26
 */
@RestController
@RequestMapping("subject")
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
public class SubjectController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private SubjectService subjectService;

    /**
     * 分页查询所有数据
     *
     * @param page    分页对象
     * @param subject 查询实体
     * @return 所有数据
     */
    @GetMapping("queryPage.do")
    public R selectAll(Page<Subject> page, Subject subject) {
        if (JavaUtils.isEmpty(subject.getCode())) {
            subject.setCode(null);
        }
        if (JavaUtils.isEmpty(subject.getName())) {
            subject.setName(null);
        }
        if (JavaUtils.isEmpty(subject.getAbbreviation())) {
            subject.setAbbreviation(null);
        }
        if (JavaUtils.isEmpty(subject.getId())) {
            subject.setId(null);
        }
        if (JavaUtils.isEmpty(subject.getCreateTime())) {
            subject.setCreateTime(null);
        }
        if (JavaUtils.isEmpty(subject.getUpdateTime())) {
            subject.setUpdateTime(null);
        }
        if (JavaUtils.isEmpty(subject.getStatus())) {
            subject.setStatus(null);
        }
        return success(this.subjectService.page(page, new QueryWrapper<>(subject)));
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
        return success(this.subjectService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param subject 实体对象
     * @return 新增结果
     */
    @PostMapping("insert.do")
    public R insert(@RequestBody Subject subject) {
        subject.preInsert();
        boolean result = this.subjectService.save(subject);
        if (result) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", subject.getId());
            return success(jsonObject);
        } else {
            return failed("执行失败");
        }
    }

    /**
     * 修改数据
     *
     * @param subject 实体对象
     * @return 修改结果
     */
    @PostMapping("updateById.do")
    public R patch(@RequestBody Subject subject) {
        String id = subject.getId();
        if (JavaUtils.isEmpty(id)) {
            return R.failed("id cannot be empty.");
        }
        subject.preUpdate();
        return success(this.subjectService.updateById(subject));
    }

    /**
     * 批量修改数据
     *
     * @param subjectList 实体对象
     * @return 修改结果
     */
    @PostMapping("updateBatchById.do")
    public R patch(@RequestBody ArrayList<Subject> subjectList) {
        for (Subject subject : subjectList) {
            String id = subject.getId();
            if (JavaUtils.isEmpty(id)) {
                return R.failed("id cannot be empty.");
            }
            subject.preUpdate();
        }
        return success(this.subjectService.updateBatchById(subjectList));
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
        return success(this.subjectService.removeById(id));
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
