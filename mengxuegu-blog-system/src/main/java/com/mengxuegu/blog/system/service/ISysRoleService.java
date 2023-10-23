package com.mengxuegu.blog.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mengxuegu.blog.entities.SysRole;
import com.mengxuegu.blog.system.req.SysRoleREQ;
import com.mengxuegu.blog.util.base.Result;

import java.util.List;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author 梦学谷-www.mengxuegu.com
 */
public interface ISysRoleService extends IService<SysRole> {

    /**
     * 条件分页查询角色列表
     * @param req
     * @return
     */
    Result queryPage(SysRoleREQ req);

    /**
     * 通过角色id删除角色信息及角色菜单关系表
     * @param id 角色id
     * @return
     */
    Result deleteById(String id);

    /**
     * 根据角色id查询此角色拥有的权限菜单ids
     * @param id 角色id
     * @return
     */
    Result findMenuIdsById(String id);

    /**
     * 新增角色菜单权限数据到 sys_role_menu
     * @param roleId 角色id
     * @param menuIds 菜单id集合
     * @return
     */
    Result saveRoleMenu(String roleId, List<String> menuIds);
}
