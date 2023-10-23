package com.mengxuegu.blog.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mengxuegu.blog.entities.SysUser;
import com.mengxuegu.blog.system.req.RegisterREQ;
import com.mengxuegu.blog.system.req.SysUserCheckPasswordREQ;
import com.mengxuegu.blog.system.req.SysUserREQ;
import com.mengxuegu.blog.system.req.SysUserUpdatePasswordREQ;
import com.mengxuegu.blog.util.base.Result;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author 梦学谷-www.mengxuegu.com
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 条件分页查询用户列表
     * @param req
     * @return
     */
    Result queryPage(SysUserREQ req);

    /**
     * 根据用户id查询当前用户所拥有的角色ids
     * @param id 用户id
     * @return
     */
    Result findRoleIdsById(String id);

    /**
     * 新增用户角色关系表数据
     * @param userId 用户id
     * @param roleIds 角色id集合
     * @return
     */
    Result saveUserRole(String userId, List<String> roleIds);

    /**
     * 根据用户id进行删除，假删除，将is_enabled 状态值更新为0
     * @param id 用户id
     * @return
     */
    Result deleteById(String id);

    /**
     * 校验原密码是否正确
     * @param req
     * @return
     */
    Result checkPassword(SysUserCheckPasswordREQ req);

    /**
     * 修改用户密码
     * @param req
     * @return
     */
    Result updatePassword(SysUserUpdatePasswordREQ req);

    /**
     * 更新用户信息
     * @param sysUser
     * @return
     */
    Result update(SysUser sysUser);

    /**
     * 查询总用户数
     * @return
     */
    Result getUserTotal();

    /**
     * 校验用户名是否存在
     * @param username
     * @return
     */
    Result checkUsername(String username);

    /**
     * 注册用户
     * @param req
     * @return
     */
    Result register(RegisterREQ req);

    /**
     * 通过用户名查询用户信息
     * @param username 用户名
     * @return
     */
    SysUser findByUsername(String username);

}
