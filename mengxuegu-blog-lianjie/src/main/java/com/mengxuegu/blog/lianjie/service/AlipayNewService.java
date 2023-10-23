package com.mengxuegu.blog.lianjie.service;

import java.util.Map;

public interface AlipayNewService {
    /**
     * 支付宝开放平台接收 request请求对象后
     * @return
     */
    String tradeCreate(String out_trade_no, String total_amount, String subject) throws Exception;
    String tradeNotify(Map<String, String> params) throws Exception;
}
