package com.example.mybatisplus.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * (SysCode)实体类
 *
 * @author makejava
 * @since 2020-09-03 16:16:23
 */
@ApiModel
@Data
public class SysCodeBO implements Serializable {
    private static final long serialVersionUID = 575155125352668567L;


    @ApiModelProperty(value = "配置id", name = "id", example = "1", dataType = "integer", required = false)
    private Integer id;

    @ApiModelProperty(value = "编号", name = "code", example = "xxx", dataType = "String", required = true)
    private String code;

    @ApiModelProperty(value = "配置类型", name = "type", example = "xxx", dataType = "String", required = true)
    private String type;

    @ApiModelProperty(value = "中文", name = "lang", example = "xxx", dataType = "String", required = true)
    private String lang;

    @ApiModelProperty(value = "备注", name = "local", example = "xxx", dataType = "String", required = false)
    private String local;

}