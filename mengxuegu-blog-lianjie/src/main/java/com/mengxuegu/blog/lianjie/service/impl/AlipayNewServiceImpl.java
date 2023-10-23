package com.mengxuegu.blog.lianjie.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.mengxuegu.blog.lianjie.service.AlipayNewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author:xianyu
 * @createDate:2022/8/1
 * @description:
 */
@Slf4j
@Service
public class AlipayNewServiceImpl implements AlipayNewService {

    @Autowired
    private AlipayClient alipayClient;

    @Autowired
    private Environment config;

    @Override
    @Transactional
    public String tradeCreate(String out_trade_no, String total_amount, String subject) throws Exception {

        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        //支付宝公共参数
        //request.setNotifyUrl("");
        request.setReturnUrl(config.getProperty("alipay.return-url"));
        //request.setNotifyUrl(config.getProperty("alipay.return-url"));
        //原始方式封装业务参数
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", "89789978987");
        bizContent.put("total_amount",100 );
        bizContent.put("subject", "反对法");
        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");

        //面向对象封装业务参数
        AlipayTradePagePayModel model =new AlipayTradePagePayModel();
        model.setOutTradeNo(out_trade_no);
        model.setTotalAmount(total_amount);
        model.setSubject(subject);
        model.setProductCode("FAST_INSTANT_TRADE_PAY");

        //bizContent.put("time_expire", "2022-08-01 22:00:00");

        //商品明细信息，按需传入
        //JSONArray goodsDetail = new JSONArray();
        //JSONObject goods1 = new JSONObject();
        //goods1.put("goods_id", "goodsNo1");
        //goods1.put("goods_name", "子商品1");
        //goods1.put("quantity", 1);
        //goods1.put("price", 0.01);
        //goodsDetail.add(goods1);
        //bizContent.put("goods_detail", goodsDetail);

        //扩展信息，按需传入
        //JSONObject extendParams = new JSONObject();
        //extendParams.put("sys_service_provider_id", "2088511833207846");
        //bizContent.put("extend_params", extendParams);

        //request.setBizContent(bizContent.toString());
        request.setBizModel(model);
        //执行请求,调用支付宝
        AlipayTradePagePayResponse response = alipayClient.pageExecute(request);
        if (response.isSuccess()) {
            log.info("调用成功,返回结果:[{}]",response.getBody());
            return response.getBody();
        } else {
            log.info("调用失败!!");
        }
        return null;
    }


    @Override
    @Transactional
    public String tradeNotify(Map<String, String> params) throws Exception{
        String result = "failure";
        try {
            //异步通知验签
            boolean signVerified = AlipaySignature.rsaCheckV1(params,
                    config.getProperty("alipay.alipay-public-key"),
                    AlipayConstants.CHARSET_UTF8,
                    AlipayConstants.SIGN_TYPE_RSA2);
            if (!signVerified) {
                // TODO 验签失败则记录异常日志，并在response中返回failure.
                log.error("支付成功,异步通知验签失败!");
                return result;
            }
            log.info("支付成功,异步通知验签成功!");
            //TODO 验签成功后，按照支付结果异步通知中的描述，对支付结果中的业务内容进行二次校验
            //1.验证out_trade_no 是否为商家系统中创建的订单号
            String outTradeNo = params.get("out_trade_no");
            //2.判断 total_amount 是否确实为该订单的实际金额
            String totalAmount = params.get("total_amount");
            //3.校验通知中的 seller_id是否为 out_trade_no 这笔单据的对应的操作方
            String sellerId = params.get("seller_id");
            if (!sellerId.equals(config.getProperty("alipay.seller-id"))) {
                log.error("商家PID校验失败");
                return result;
            }
            //4.验证 app_id 是否为该商家本身
            String appId = params.get("app_id");
            if (!appId.equals(config.getProperty("alipay.app.id"))){
                log.error("app_id校验失败");
                return result;
            }
            //在支付宝的业务通知中，只有交易通知状态为 TRADE_SUCCESS 或 TRADE_FINISHED 时，支付宝才会认定为买家付款成功
            String tradeStatus = params.get("trade_status");
            if (!"TRADE_SUCCESS".equals(tradeStatus) && !"TRADE_FINISHED".equals(tradeStatus)){
                log.error("支付未成功");
                return result;
            }

            //TODO 处理自身业务


            result = "success";
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        return result;
    }

}