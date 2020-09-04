package com.example.mybatisplus.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author haha
 */
@Data
public class UserHasRoleVO implements Serializable {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 电话
     */
    private String tel;

    /**
     * 角色列表
     */
    private List<RoleVO> roles;

}
