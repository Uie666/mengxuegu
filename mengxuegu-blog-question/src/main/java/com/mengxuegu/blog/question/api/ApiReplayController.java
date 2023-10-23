package com.mengxuegu.blog.question.api;

import com.mengxuegu.blog.question.service.IReplayService;
import com.mengxuegu.blog.util.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "回答管理API接口", description = "回答管理API接口, 不需要通过身份认证就可以访问")
@RestController
@RequestMapping("/api/replay")
public class ApiReplayController {

    @Autowired
    private IReplayService replayService;

    @ApiImplicitParam(name = "questionId", value = "问题ID", required = true)
    @ApiOperation("通过问题ID递归所有的回答及子评论信息")
    @GetMapping("/list/{questionId}")
    public Result findByQuestionId(@PathVariable String questionId) {
        return replayService.findByQuestionId(questionId);
    }


}
