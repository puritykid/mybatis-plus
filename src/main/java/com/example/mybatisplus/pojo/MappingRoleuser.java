package com.example.mybatisplus.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * mapping_roleuser
 * @author 
 */
@Data
public class MappingRoleuser implements Serializable {
    /**
     * 主键id
     */
    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 角色id
     */
    private String roleId;

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