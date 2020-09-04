package com.example.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;

/**
 * (SysCode)实体类
 *
 * @author makejava
 * @since 2020-09-03 16:16:23
 */
@Data
public class SysCode implements Serializable {
    private static final long serialVersionUID = 575155125352668567L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String code;

    private String type;

    private String local;

    private String lang;

    private String remark;

    private String createdById;

    private String createdAt;

    private String updatedById;

    private String updatedAt;

    private String deletedById;

    private String deletedAt;

    @TableLogic(value = "0",delval = "1")
    private Integer deleted;

}