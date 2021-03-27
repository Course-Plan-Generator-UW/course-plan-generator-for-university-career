package edu.wisc.scc.controller;


import common.JavaUtils;
import common.IdDto;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.wisc.scc.entity.Section;
import edu.wisc.scc.service.SectionService;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import javax.annotation.Resource;

/**
 * (Section)表控制层
 *
 * @author Gao Qi
 * @since 2021-03-24 06:53:10
 */
@RestController
@RequestMapping("section")
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
public class SectionController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private SectionService sectionService;

    /**
     * 分页查询所有数据
     *
     * @param page    分页对象
     * @param section 查询实体
     * @return 所有数据
     */
    @GetMapping("queryPage.do")
    public R selectAll(Page<Section> page, Section section) {
        if (JavaUtils.isEmpty(section.getId())) {
            section.setId(null);
        }
        if (JavaUtils.isEmpty(section.getCourseOfferingUuid())) {
            section.setCourseOfferingUuid(null);
        }
        if (JavaUtils.isEmpty(section.getSectionType())) {
            section.setSectionType(null);
        }
        if (JavaUtils.isEmpty(section.getNumber())) {
            section.setNumber(null);
        }
        if (JavaUtils.isEmpty(section.getRoomUuid())) {
            section.setRoomUuid(null);
        }
        if (JavaUtils.isEmpty(section.getScheduleUuid())) {
            section.setScheduleUuid(null);
        }
        if (JavaUtils.isEmpty(section.getCreateTime())) {
            section.setCreateTime(null);
        }
        if (JavaUtils.isEmpty(section.getUpdateTime())) {
            section.setUpdateTime(null);
        }
        if (JavaUtils.isEmpty(section.getStatus())) {
            section.setStatus(null);
        }
        return success(this.sectionService.page(page, new QueryWrapper<>(section)));
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
        return success(this.sectionService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param section 实体对象
     * @return 新增结果
     */
    @PostMapping("insert.do")
    public R insert(@RequestBody Section section) {
        section.preInsert();
        boolean result = this.sectionService.save(section);
        if (result) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", section.getId());
            return success(jsonObject);
        } else {
            return failed("执行失败");
        }
    }

    /**
     * 修改数据
     *
     * @param section 实体对象
     * @return 修改结果
     */
    @PostMapping("updateById.do")
    public R patch(@RequestBody Section section) {
        String id = section.getId();
        if (JavaUtils.isEmpty(id)) {
            return R.failed("id cannot be empty.");
        }
        section.preUpdate();
        return success(this.sectionService.updateById(section));
    }

    /**
     * 批量修改数据
     *
     * @param sectionList 实体对象
     * @return 修改结果
     */
    @PostMapping("updateBatchById.do")
    public R patch(@RequestBody ArrayList<Section> sectionList) {
        for (Section section : sectionList) {
            String id = section.getId();
            if (JavaUtils.isEmpty(id)) {
                return R.failed("id cannot be empty.");
            }
            section.preUpdate();
        }
        return success(this.sectionService.updateBatchById(sectionList));
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
        return success(this.sectionService.removeById(id));
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
