package com.mengxuegu.blog.lianjie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mengxuegu.blog.entities.LianjieResume;
import com.mengxuegu.blog.lianjie.req.LianJieREQ;
import com.mengxuegu.blog.util.base.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 梦学谷-www.mengxuegu.com
 * @since 2023-09-19
 */
public interface ILianjieResumeService extends IService<LianjieResume> {


    /**
     * 条件分页查询用户列表
     * @param req
     * @return
     */
    Result queryPage(LianJieREQ req);
    LianjieResume getById(String id);
}
