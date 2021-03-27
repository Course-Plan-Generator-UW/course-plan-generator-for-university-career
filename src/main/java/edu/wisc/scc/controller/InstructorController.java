package edu.wisc.scc.controller;


import common.JavaUtils;
import common.IdDto;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.wisc.scc.entity.Instructor;
import edu.wisc.scc.service.InstructorService;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import javax.annotation.Resource;

/**
 * (Instructor)表控制层
 *
 * @author Gao Qi
 * @since 2021-03-24 07:19:10
 */
@RestController
@RequestMapping("instructor")
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
public class InstructorController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private InstructorService instructorService;

    /**
     * 分页查询所有数据
     *
     * @param page       分页对象
     * @param instructor 查询实体
     * @return 所有数据
     */
    @GetMapping("queryPage.do")
    public R selectAll(Page<Instructor> page, Instructor instructor) {
        if (JavaUtils.isEmpty(instructor.getInsNumber())) {
            instructor.setInsNumber(null);
        }
        if (JavaUtils.isEmpty(instructor.getName())) {
            instructor.setName(null);
        }
        if (JavaUtils.isEmpty(instructor.getId())) {
            instructor.setId(null);
        }
        if (JavaUtils.isEmpty(instructor.getCreateTime())) {
            instructor.setCreateTime(null);
        }
        if (JavaUtils.isEmpty(instructor.getUpdateTime())) {
            instructor.setUpdateTime(null);
        }
        if (JavaUtils.isEmpty(instructor.getStatus())) {
            instructor.setStatus(null);
        }
        return success(this.instructorService.page(page, new QueryWrapper<>(instructor)));
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
        return success(this.instructorService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param instructor 实体对象
     * @return 新增结果
     */
    @PostMapping("insert.do")
    public R insert(@RequestBody Instructor instructor) {
        instructor.preInsert();
        boolean result = this.instructorService.save(instructor);
        if (result) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", instructor.getId());
            return success(jsonObject);
        } else {
            return failed("执行失败");
        }
    }

    /**
     * 修改数据
     *
     * @param instructor 实体对象
     * @return 修改结果
     */
    @PostMapping("updateById.do")
    public R patch(@RequestBody Instructor instructor) {
        String id = instructor.getId();
        if (JavaUtils.isEmpty(id)) {
            return R.failed("id cannot be empty.");
        }
        instructor.preUpdate();
        return success(this.instructorService.updateById(instructor));
    }

    /**
     * 批量修改数据
     *
     * @param instructorList 实体对象
     * @return 修改结果
     */
    @PostMapping("updateBatchById.do")
    public R patch(@RequestBody ArrayList<Instructor> instructorList) {
        for (Instructor instructor : instructorList) {
            String id = instructor.getId();
            if (JavaUtils.isEmpty(id)) {
                return R.failed("id cannot be empty.");
            }
            instructor.preUpdate();
        }
        return success(this.instructorService.updateBatchById(instructorList));
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
        return success(this.instructorService.removeById(id));
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
