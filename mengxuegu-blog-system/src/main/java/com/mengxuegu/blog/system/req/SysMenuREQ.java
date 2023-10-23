package com.mengxuegu.blog.system.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(value = "SysMenuREQ对象", description = "菜单列表查询条件")
public class SysMenuREQ {

    @ApiModelProperty(value = "菜单名称")
    private String name;

}
