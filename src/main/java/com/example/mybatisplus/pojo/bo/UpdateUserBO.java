package com.example.mybatisplus.pojo.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 数据传输实体
 *
 * @author haha
 */
@Data
public class UpdateUserBO implements Serializable {

    @ApiModelProperty(value = "用户id（修改必输）", name = "userId", example = "xxx", dataType = "String", required = true)
    private String userId;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", name = "userName", example = "xxx", dataType = "String", required = true)
    private String userName;

    /**
     * 登录名
     */
    @ApiModelProperty(value = "登录名", name = "loginName", example = "xxx", dataType = "String", required = true)
    private String loginName;

    /**
     * 电话
     */
    @ApiModelProperty(value = "电话", name = "tel", example = "xxx", dataType = "String", required = true)
    private String tel;
}
