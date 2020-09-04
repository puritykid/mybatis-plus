package com.example.mybatisplus.convert;

import com.example.mybatisplus.pojo.SysCode;
import com.example.mybatisplus.pojo.vo.SysCodeVO;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description: 系统配置转换类
 * @Author: chenxiaojun
 * @CreateDate: 2019/7/30 10:00
 * @Version: 1.0
 */
@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface SysConfigConverter {

    SysConfigConverter MAPPER = Mappers.getMapper(SysConfigConverter.class);

    /**
     * 系统配置实体映射
     * @param sysCodeList
     * @return
     */
    List<SysCodeVO> doMapList(List<SysCode> sysCodeList);

    /**
     * 系统配置实体映射
     * @param sysCode
     * @return
     */
    SysCodeVO doMap(SysCode sysCode);

}

