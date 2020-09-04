package com.example.mybatisplus.pojo;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * user
 * @author 
 */
@Data
@TableName("user")
public class User implements Serializable {
    /**
     * 用户id
     */
    @TableId(type = IdType.INPUT)
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
     * 密码
     */
    private String loginPass;

    /**
     * 电话
     */
    private String tel;

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
    private String updatedAt;

    /**
     * 删除人
     */
    private String deletedById;

    /**
     * 删除时间
     */
    private String deletedAt;

    /**
     * 删除标志
     */
    @TableLogic(value = "0",delval = "0")
    private boolean deleted;

    private static final long serialVersionUID = 1L;
}