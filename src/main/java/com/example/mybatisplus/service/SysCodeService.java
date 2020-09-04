package com.example.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mybatisplus.pojo.SysCode;
import com.example.mybatisplus.pojo.bo.SysCodeBO;

import java.util.List;

/**
 * (SysCode)表服务接口
 *
 * @author makejava
 * @since 2020-09-03 16:16:25
 */
public interface SysCodeService extends IService<SysCode> {

    /**
     * 获取所有配置列表
     *
     * @return 配置集合
     */
    List<SysCode> selectSysCodeList();

    /**
     * 创建配置
     *
     * @param sysCodeBO
     * @return
     */
    SysCode createSysCode(SysCodeBO sysCodeBO);

    /**
     * 修改配置
     *
     * @param sysCodeBO
     * @return
     */
    void modifySysCode(SysCodeBO sysCodeBO);

    /**
     * 删除配置
     *
     * @param id
     * @return
     */
    void deleteSysCode(String id);
}