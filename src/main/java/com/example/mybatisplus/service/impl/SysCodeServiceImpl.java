package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mybatisplus.mapper.SysCodeMapper;
import com.example.mybatisplus.pojo.SysCode;
import com.example.mybatisplus.pojo.bo.SysCodeBO;
import com.example.mybatisplus.service.SysCodeService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (SysCode)表服务实现类
 *
 * @author makejava
 * @since 2020-09-03 16:16:26
 */
@Service
public class SysCodeServiceImpl extends ServiceImpl<SysCodeMapper, SysCode> implements SysCodeService {


    @Override
    public List<SysCode> selectSysCodeList() {
        LambdaQueryWrapper<SysCode> wrapper = Wrappers.<SysCode>lambdaQuery()
                .select(SysCode::getCode,
                        SysCode::getChinese,
                        SysCode::getEnglish);
        return this.baseMapper.selectList(wrapper);
    }


    @Override
    public SysCode createSysCode(SysCodeBO sysCodeBO) {

        SysCode sysCode = new SysCode();
        BeanUtils.copyProperties(sysCodeBO,sysCode);
        this.baseMapper.insert(sysCode);


        return null;
    }

    @Override
    public void modifySysCode(SysCodeBO sysCodeBO) {

    }

    @Override
    public void deleteSysCode(String code) {

    }
}