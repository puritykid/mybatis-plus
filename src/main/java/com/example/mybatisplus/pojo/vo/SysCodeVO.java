package com.example.mybatisplus.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * (SysCode)实体类
 *
 * @author makejava
 * @since 2020-09-03 16:16:23
 */
@Data
public class SysCodeVO implements Serializable {
    private static final long serialVersionUID = 575155125352668567L;


    private Integer id;

    private String code;

    private String type;

    private String lang;

    private String local;

}