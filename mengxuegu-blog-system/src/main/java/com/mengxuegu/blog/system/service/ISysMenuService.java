package com.mengxuegu.blog.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mengxuegu.blog.entities.SysMenu;
import com.mengxuegu.blog.system.req.SysMenuREQ;
import com.mengxuegu.blog.util.base.Result;

import java.util.List;

/**
 * <p>
 * 菜单信息表 服务类
 * </p>
 *
 * @author 梦学谷-www.mengxuegu.com
 */
public interface ISysMenuService extends IService<SysMenu> {

    /**
     * 条件查询菜单列表
     * @param req
     * @return
     */
    Result queryList(SysMenuREQ req);

    /**
     * 根据菜单id删除
     * @param id 菜单id
     * @return
     */
    Result deleteById(String id);

    /**
     * 通过用户id查询所拥有的权限菜单树
     * @param userId
     * @return
     */
    Result findUserMenuTree(String userId);

    /**
     * 通过用户id查询所拥有的权限信息
     * @param userId
     * @return
     */
    List<SysMenu> findByUserId(String userId);
}
