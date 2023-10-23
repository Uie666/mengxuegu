package com.mengxuegu.blog.oauth2.config;

import com.alibaba.fastjson.JSON;
import com.mengxuegu.blog.oauth2.service.JwtUser;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 扩展响应的认证信息
 */
@Component
public class JwtTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken,
                                     OAuth2Authentication oAuth2Authentication) {
        JwtUser user = (JwtUser) oAuth2Authentication.getPrincipal();
        Map<String, Object> map = new HashMap<>();
        map.put("userInfo", JSON.toJSON(user));

        // 设置附加信息
        ( (DefaultOAuth2AccessToken)oAuth2AccessToken ).setAdditionalInformation(map);

        return oAuth2AccessToken;
    }

}
