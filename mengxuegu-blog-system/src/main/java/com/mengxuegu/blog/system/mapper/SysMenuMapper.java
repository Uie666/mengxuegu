package com.mengxuegu.blog.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mengxuegu.blog.entities.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单信息表 Mapper 接口
 * </p>
 *
 * @author 梦学谷-www.mengxuegu.com
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 查询指定用户id所拥有的菜单权限（目录、菜单、按钮）
     * @param userId
     * @return
     */
    List<SysMenu> findByUserId(@Param("userId") String userId);
}
