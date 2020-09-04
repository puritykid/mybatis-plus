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

    @ApiModelProperty(value = "配置id", name = "id", example = "1", dataType = "integer", required = false,position = 1)
    private Integer id;

    @ApiModelProperty(value = "编号", name = "code", example = "xxx", dataType = "String", required = true,position = 3)
    private String code;

    @ApiModelProperty(value = "配置类型", name = "type", example = "xxx", dataType = "String", required = true,position = 5)
    private String type;

    @ApiModelProperty(value = "中文", name = "chinese", example = "xxx", dataType = "String", required = true,position = 7)
    private String chinese;

    @ApiModelProperty(value = "英文", name = "english", example = "xxx", dataType = "String", required = true,position = 9)
    private String english;

    @ApiModelProperty(value = "备注", name = "remark", example = "xxx", dataType = "String", required = false,position = 12)
    private String remark;

}