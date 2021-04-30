package edu.wisc.scc.controller;


import common.JavaUtils;
import common.IdDto;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.wisc.scc.entity.SubjectInfo;
import edu.wisc.scc.service.SubjectInfoService;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import javax.annotation.Resource;

/**
 * subject info(SubjectInfo)表控制层
 *
 * @author 蔺春华
 * @since 2021-03-28 11:34:51
 */
@RestController
@RequestMapping("subjectInfo")
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
public class SubjectInfoController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private SubjectInfoService subjectInfoService;

    @GetMapping("listAll.do")
    public R listAll(){
        return R.ok(this.subjectInfoService.list());
    }

    /**
     * 分页查询所有数据
     *
     * @param page        分页对象
     * @param subjectInfo 查询实体
     * @return 所有数据
     */
    @GetMapping("queryPage.do")
    public R selectAll(Page<SubjectInfo> page, SubjectInfo subjectInfo) {
        if (JavaUtils.isEmpty(subjectInfo.getId())) {
            subjectInfo.setId(null);
        }
        if (JavaUtils.isEmpty(subjectInfo.getStatus())) {
            subjectInfo.setStatus(null);
        }
        if (JavaUtils.isEmpty(subjectInfo.getCreateTime())) {
            subjectInfo.setCreateTime(null);
        }
        if (JavaUtils.isEmpty(subjectInfo.getUpdateTime())) {
            subjectInfo.setUpdateTime(null);
        }
        if (JavaUtils.isEmpty(subjectInfo.getSubjectName())) {
            subjectInfo.setSubjectName(null);
        }
        return success(this.subjectInfoService.page(page, new QueryWrapper<>(subjectInfo)));
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
        return success(this.subjectInfoService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param subjectInfo 实体对象
     * @return 新增结果
     */
    @PostMapping("insert.do")
    public R insert(@RequestBody SubjectInfo subjectInfo) {
        subjectInfo.preInsert();
        boolean result = this.subjectInfoService.save(subjectInfo);
        if (result) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", subjectInfo.getId());
            return success(jsonObject);
        } else {
            return failed("执行失败");
        }
    }

    /**
     * 修改数据
     *
     * @param subjectInfo 实体对象
     * @return 修改结果
     */
    @PostMapping("updateById.do")
    public R patch(@RequestBody SubjectInfo subjectInfo) {
        String id = subjectInfo.getId();
        if (JavaUtils.isEmpty(id)) {
            return R.failed("id cannot be empty.");
        }
        subjectInfo.preUpdate();
        return success(this.subjectInfoService.updateById(subjectInfo));
    }

    /**
     * 批量修改数据
     *
     * @param subjectInfoList 实体对象
     * @return 修改结果
     */
    @PostMapping("updateBatchById.do")
    public R patch(@RequestBody ArrayList<SubjectInfo> subjectInfoList) {
        for (SubjectInfo subjectInfo : subjectInfoList) {
            String id = subjectInfo.getId();
            if (JavaUtils.isEmpty(id)) {
                return R.failed("id cannot be empty.");
            }
            subjectInfo.preUpdate();
        }
        return success(this.subjectInfoService.updateBatchById(subjectInfoList));
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
        return success(this.subjectInfoService.removeById(id));
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
