package edu.wisc.scc.controller;


import common.JavaUtils;
import common.IdDto;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.wisc.scc.entity.GradeDistribution;
import edu.wisc.scc.service.GradeDistributionService;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import javax.annotation.Resource;

/**
 * (GradeDistribution)表控制层
 *
 * @author Gao Qi
 * @since 2021-03-24 06:38:49
 */
@RestController
@RequestMapping("gradeDistribution")
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
public class GradeDistributionController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private GradeDistributionService gradeDistributionService;

    /**
     * 分页查询所有数据
     *
     * @param page              分页对象
     * @param gradeDistribution 查询实体
     * @return 所有数据
     */
    @GetMapping("queryPage.do")
    public R selectAll(Page<GradeDistribution> page, GradeDistribution gradeDistribution) {
        if (JavaUtils.isEmpty(gradeDistribution.getCourseOfferingUuid())) {
            gradeDistribution.setCourseOfferingUuid(null);
        }
        if (JavaUtils.isEmpty(gradeDistribution.getSectionNumber())) {
            gradeDistribution.setSectionNumber(null);
        }
        if (JavaUtils.isEmpty(gradeDistribution.getNCount())) {
            gradeDistribution.setNCount(null);
        }
        if (JavaUtils.isEmpty(gradeDistribution.getCCount())) {
            gradeDistribution.setCCount(null);
        }
        if (JavaUtils.isEmpty(gradeDistribution.getFCount())) {
            gradeDistribution.setFCount(null);
        }
        if (JavaUtils.isEmpty(gradeDistribution.getCrCount())) {
            gradeDistribution.setCrCount(null);
        }
        if (JavaUtils.isEmpty(gradeDistribution.getPCount())) {
            gradeDistribution.setPCount(null);
        }
        if (JavaUtils.isEmpty(gradeDistribution.getNwCount())) {
            gradeDistribution.setNwCount(null);
        }
        if (JavaUtils.isEmpty(gradeDistribution.getOtherCount())) {
            gradeDistribution.setOtherCount(null);
        }
        if (JavaUtils.isEmpty(gradeDistribution.getBcCount())) {
            gradeDistribution.setBcCount(null);
        }
        if (JavaUtils.isEmpty(gradeDistribution.getUCount())) {
            gradeDistribution.setUCount(null);
        }
        if (JavaUtils.isEmpty(gradeDistribution.getDCount())) {
            gradeDistribution.setDCount(null);
        }
        if (JavaUtils.isEmpty(gradeDistribution.getAbCount())) {
            gradeDistribution.setAbCount(null);
        }
        if (JavaUtils.isEmpty(gradeDistribution.getACount())) {
            gradeDistribution.setACount(null);
        }
        if (JavaUtils.isEmpty(gradeDistribution.getSCount())) {
            gradeDistribution.setSCount(null);
        }
        if (JavaUtils.isEmpty(gradeDistribution.getICount())) {
            gradeDistribution.setICount(null);
        }
        if (JavaUtils.isEmpty(gradeDistribution.getBCount())) {
            gradeDistribution.setBCount(null);
        }
        if (JavaUtils.isEmpty(gradeDistribution.getNrCount())) {
            gradeDistribution.setNrCount(null);
        }
        if (JavaUtils.isEmpty(gradeDistribution.getId())) {
            gradeDistribution.setId(null);
        }
        if (JavaUtils.isEmpty(gradeDistribution.getCreateTime())) {
            gradeDistribution.setCreateTime(null);
        }
        if (JavaUtils.isEmpty(gradeDistribution.getUpdateTime())) {
            gradeDistribution.setUpdateTime(null);
        }
        if (JavaUtils.isEmpty(gradeDistribution.getStatus())) {
            gradeDistribution.setStatus(null);
        }
        return success(this.gradeDistributionService.page(page, new QueryWrapper<>(gradeDistribution)));
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
        return success(this.gradeDistributionService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param gradeDistribution 实体对象
     * @return 新增结果
     */
    @PostMapping("insert.do")
    public R insert(@RequestBody GradeDistribution gradeDistribution) {
        gradeDistribution.preInsert();
        boolean result = this.gradeDistributionService.save(gradeDistribution);
        if (result) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", gradeDistribution.getId());
            return success(jsonObject);
        } else {
            return failed("执行失败");
        }
    }

    /**
     * 修改数据
     *
     * @param gradeDistribution 实体对象
     * @return 修改结果
     */
    @PostMapping("updateById.do")
    public R patch(@RequestBody GradeDistribution gradeDistribution) {
        String id = gradeDistribution.getId();
        if (JavaUtils.isEmpty(id)) {
            return R.failed("id cannot be empty.");
        }
        gradeDistribution.preUpdate();
        return success(this.gradeDistributionService.updateById(gradeDistribution));
    }

    /**
     * 批量修改数据
     *
     * @param gradeDistributionList 实体对象
     * @return 修改结果
     */
    @PostMapping("updateBatchById.do")
    public R patch(@RequestBody ArrayList<GradeDistribution> gradeDistributionList) {
        for (GradeDistribution gradeDistribution : gradeDistributionList) {
            String id = gradeDistribution.getId();
            if (JavaUtils.isEmpty(id)) {
                return R.failed("id cannot be empty.");
            }
            gradeDistribution.preUpdate();
        }
        return success(this.gradeDistributionService.updateBatchById(gradeDistributionList));
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
        return success(this.gradeDistributionService.removeById(id));
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
