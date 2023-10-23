package com.mengxuegu.blog.lianjie.req;

import com.mengxuegu.blog.entities.LianjieResume;
import com.mengxuegu.blog.util.base.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(value = "LianJieREQ对象", description = "简历查询条件")
public class LianJieREQ extends BaseRequest<LianjieResume> {
    @ApiModelProperty(value = "用户名")
    private String name;

    @ApiModelProperty(value = "手机号")
    private String phone;

}
