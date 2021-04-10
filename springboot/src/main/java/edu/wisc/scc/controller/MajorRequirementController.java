package edu.wisc.scc.controller;


import common.JavaUtils;
import common.IdDto;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.wisc.scc.entity.MajorRequirement;
import edu.wisc.scc.service.MajorRequirementService;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import javax.annotation.Resource;

/**
 * major requirement(MajorRequirement)表控制层
 *
 * @author 蔺春华
 * @since 2021-04-07 20:10:57
 */
@RestController
@RequestMapping("majorRequirement")
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
public class MajorRequirementController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private MajorRequirementService majorRequirementService;

    /**
     * 分页查询所有数据
     *
     * @param page             分页对象
     * @param majorRequirement 查询实体
     * @return 所有数据
     */
    @GetMapping("queryPage.do")
    public R selectAll(Page<MajorRequirement> page, MajorRequirement majorRequirement) {
        if (JavaUtils.isEmpty(majorRequirement.getId())) {
            majorRequirement.setId(null);
        }
        if (JavaUtils.isEmpty(majorRequirement.getStatus())) {
            majorRequirement.setStatus(null);
        }
        if (JavaUtils.isEmpty(majorRequirement.getCreateTime())) {
            majorRequirement.setCreateTime(null);
        }
        if (JavaUtils.isEmpty(majorRequirement.getUpdateTime())) {
            majorRequirement.setUpdateTime(null);
        }
        if (JavaUtils.isEmpty(majorRequirement.getMajorName())) {
            majorRequirement.setMajorName(null);
        }
        if (JavaUtils.isEmpty(majorRequirement.getCourseName())) {
            majorRequirement.setCourseName(null);
        }
        if (JavaUtils.isEmpty(majorRequirement.getCourseSection())) {
            majorRequirement.setCourseSection(null);
        }
        if (JavaUtils.isEmpty(majorRequirement.getCreditsNeedInSection())) {
            majorRequirement.setCreditsNeedInSection(null);
        }
        return success(this.majorRequirementService.page(page, new QueryWrapper<>(majorRequirement)));
    }

    @GetMapping("queryAll.do")
    public R selectAll(MajorRequirement majorRequirement) {
        if (JavaUtils.isEmpty(majorRequirement.getId())) {
            majorRequirement.setId(null);
        }
        if (JavaUtils.isEmpty(majorRequirement.getStatus())) {
            majorRequirement.setStatus(null);
        }
        if (JavaUtils.isEmpty(majorRequirement.getMajorName())) {
            majorRequirement.setMajorName(null);
        }
        if (JavaUtils.isEmpty(majorRequirement.getCourseName())) {
            majorRequirement.setCourseName(null);
        }
        if (JavaUtils.isEmpty(majorRequirement.getCourseSection())) {
            majorRequirement.setCourseSection(null);
        }
        if (JavaUtils.isEmpty(majorRequirement.getCreditsNeedInSection())) {
            majorRequirement.setCreditsNeedInSection(null);
        }
        return success(this.majorRequirementService.list(new QueryWrapper<>(majorRequirement)));
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
        return success(this.majorRequirementService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param majorRequirement 实体对象
     * @return 新增结果
     */
    @PostMapping("insert.do")
    public R insert(@RequestBody MajorRequirement majorRequirement) {
        majorRequirement.preInsert();
        boolean result = this.majorRequirementService.save(majorRequirement);
        if (result) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", majorRequirement.getId());
            return success(jsonObject);
        } else {
            return failed("执行失败");
        }
    }

    /**
     * 修改数据
     *
     * @param majorRequirement 实体对象
     * @return 修改结果
     */
    @PostMapping("updateById.do")
    public R patch(@RequestBody MajorRequirement majorRequirement) {
        String id = majorRequirement.getId();
        if (JavaUtils.isEmpty(id)) {
            return R.failed("id cannot be empty.");
        }
        majorRequirement.preUpdate();
        return success(this.majorRequirementService.updateById(majorRequirement));
    }

    /**
     * 批量修改数据
     *
     * @param majorRequirementList 实体对象
     * @return 修改结果
     */
    @PostMapping("updateBatchById.do")
    public R patch(@RequestBody ArrayList<MajorRequirement> majorRequirementList) {
        for (MajorRequirement majorRequirement : majorRequirementList) {
            String id = majorRequirement.getId();
            if (JavaUtils.isEmpty(id)) {
                return R.failed("id cannot be empty.");
            }
            majorRequirement.preUpdate();
        }
        return success(this.majorRequirementService.updateBatchById(majorRequirementList));
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
        return success(this.majorRequirementService.removeById(id));
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
