package com.example.mybatisplus.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * role
 * @author 
 */
@Data
public class Role implements Serializable {
    /**
     * 角色id
     */
    private String roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 创建人
     */
    private String createdById;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新人
     */
    private String updatedById;

    /**
     * 更新时间
     */
    private Date updatedAt;

    /**
     * 删除人
     */
    private String deletedById;

    /**
     * 删除时间
     */
    private Date deletedAt;

    private static final long serialVersionUID = 1L;
}