package com.mengxuegu.blog.lianjie.controller;

import com.alibaba.fastjson.JSON;
import com.mengxuegu.blog.lianjie.service.AlipayNewService;
import com.mengxuegu.blog.util.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author:xianyu
 * @createDate:2022/7/30
 * @description:
 */
@CrossOrigin
@RestController
@RequestMapping("/yun")
@Api(tags = "网站支付宝支付")
@Slf4j
public class AlipayController {

    @Autowired
    private AlipayNewService alipayService;
    @ApiOperation("统一收单下单并支付接口调用")
    @GetMapping("/scanPay")
    public Result tradePagePay(@RequestParam(value = "out_trade_no", required = true)
                                   String out_trade_no,@RequestParam(value = "total_amount", required = true) String total_amount,
                               @RequestParam (value = "subject", required = true) String subject) throws Exception {
        log.info("统一收单下单并支付接口调用");
        /*
        支付宝开放平台接收 request 请求对象后
        会为开发者生成一个html形式的form表单,包含自动提交的脚本
         */
        System.out.println("out_trade_no:" + out_trade_no);
        String formStr = alipayService.tradeCreate(out_trade_no, total_amount, subject);
        /*
        将form表单字符串返回给前端
        前端自动提交脚本
        表单会自动提交到 action熟悉只想的支付宝开放平台中 为用户展示一个支付页面
         */
        return Result.ok(formStr);
    }

    /**
     * 支付宝回调
     *
     * @param params
     * @return
     */
    @ApiOperation("交易通知")
    @PostMapping("/tradeNotify")
    public String tradeNotify(@RequestParam Map<String, String> params) throws Exception{
        log.info("支付通知,正在执行,通知参数:{}", JSON.toJSONString(params));
        return alipayService.tradeNotify(params);
    }

}