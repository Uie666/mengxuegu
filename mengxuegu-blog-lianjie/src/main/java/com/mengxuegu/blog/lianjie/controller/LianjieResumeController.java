package com.mengxuegu.blog.lianjie.controller;


import com.mengxuegu.blog.entities.LianjieResume;
import com.mengxuegu.blog.lianjie.req.LianJieREQ;
import com.mengxuegu.blog.lianjie.service.ILianjieResumeService;
import com.mengxuegu.blog.util.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 梦学谷-www.mengxuegu.com
 * @since 2023-09-19
 */
@Api(value = "简历管理", description = "管理接口, 提供简历的增删改查")
@RestController
@RequestMapping("/lianjieresume")
public class LianjieResumeController {
    @Autowired
    private ILianjieResumeService lianjieResumeService;

    @ApiModelProperty(value = "简历查询分页列表接口")
    @PostMapping("/search")
    public Result search(@RequestBody LianJieREQ req) {
        return lianjieResumeService.queryPage(req);
    }

    @ApiImplicitParam(name = "id", value = "简历ID", required = true)
    @ApiOperation(value = "简历详情接口")
    @GetMapping("/{id}")
    public Result view(@PathVariable("id") String id) {
        return Result.ok(lianjieResumeService.getById(id));
    }

    @ApiModelProperty(value = "简历新增接口")
    @PostMapping
    public Result save(@RequestBody LianjieResume lianjieResume) {
        lianjieResumeService.save(lianjieResume);
        return Result.ok();
    }
    @ApiModelProperty(value = "简历修改接口")
    @PutMapping
    public Result update(@RequestBody LianjieResume lianjieResume) {
        lianjieResume.setUpdateDate(new Date());
        lianjieResumeService.updateById(lianjieResume);
        return Result.ok();
    }
    @ApiImplicitParam(name = "id", value = "简历ID", required = true)
    @ApiModelProperty(value = "简历删除接口")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") String id) {
        lianjieResumeService.getBaseMapper().deleteById(id);
        return Result.ok();
    }
}
