package com.mengxuegu.blog.lianjie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mengxuegu.blog.entities.LianjieResume;
import com.mengxuegu.blog.lianjie.mapper.LianjieResumeMapper;
import com.mengxuegu.blog.lianjie.req.LianJieREQ;
import com.mengxuegu.blog.lianjie.service.ILianjieResumeService;
import com.mengxuegu.blog.util.base.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 梦学谷-www.mengxuegu.com
 * @since 2023-09-19
 */
@Service
public class LianjieResumeServiceImpl extends ServiceImpl<LianjieResumeMapper, LianjieResume> implements ILianjieResumeService {
    @Resource
    private RedisTemplate<String, LianjieResume> redisTemplate;

    @Override
    public Result queryPage(LianJieREQ req) {
        QueryWrapper<LianjieResume> wrapper = new QueryWrapper<>();

        if (StringUtils.isNotEmpty(req.getName())) {
            wrapper.like("name", req.getName());
        }

        if (StringUtils.isNotEmpty(req.getPhone())) {
            wrapper.like("phone", req.getPhone());
        }
        wrapper.orderByDesc("update_date");
        return Result.ok(baseMapper.selectPage(req.getPage(), wrapper));
    }

    @Override
    public LianjieResume getById(String id) {

        // 基于ID生成缓存键
        String cacheKey = "lianjieResume:" + id;

        // 尝试从缓存中检索数据
        LianjieResume cachedData = redisTemplate.opsForValue().get(cacheKey);

        if (cachedData != null) {
            // 如果在缓存中找到数据，则返回它
            return cachedData;
        }

        // 从数据库中获取数据
        LianjieResume result = baseMapper.selectById(id);

        if (result != null) {
            // 将数据存储在缓存中
            redisTemplate.opsForValue().set(cacheKey, result);
        }

        return result;
    }
}
