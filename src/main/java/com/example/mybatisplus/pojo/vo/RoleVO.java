package com.example.mybatisplus.pojo.vo;

import lombok.Data;

import java.io.Serializable;
@Data
public class RoleVO implements Serializable {

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 角色名称
     */
    private String roleName;
}
