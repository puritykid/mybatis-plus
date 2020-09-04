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
public class UserBO implements Serializable {

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", name = "userName", example = "xxx", dataType = "String", required = true,position = 1)
    private String userName;

    /**
     * 登录名
     */
    @ApiModelProperty(value = "登录名", name = "loginName", example = "xxx", dataType = "String", required = true,position = 3)
    private String loginName;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", name = "loginPass", example = "xxx", dataType = "String", required = true,position = 5)
    private String loginPass;

    /**
     * 电话
     */
    @ApiModelProperty(value = "电话", name = "tel", example = "xxx", dataType = "String", required = true,position = 7)
    private String tel;
}
