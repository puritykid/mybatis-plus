package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mybatisplus.config.LocalConfig;
import com.example.mybatisplus.mapper.SysCodeMapper;
import com.example.mybatisplus.pojo.SysCode;
import com.example.mybatisplus.pojo.bo.SysCodeBO;
import com.example.mybatisplus.service.SysCodeService;
import com.example.mybatisplus.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

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
                .select(SysCode::getId,
                        SysCode::getType,
                        SysCode::getCode,
                        SysCode::getLang,
                        SysCode::getLocal);
        return this.baseMapper.selectList(wrapper);
    }


    @Override
    public SysCode createSysCode(SysCodeBO sysCodeBO) {

        SysCode sysCode = new SysCode();
        BeanUtils.copyProperties(sysCodeBO, sysCode);
        sysCode.setCreatedById(UUID.randomUUID().toString());
        sysCode.setCreatedAt(DateUtils.now());
        sysCode.setDeleted(0);
        this.baseMapper.insert(sysCode);
        updateLocalConfig();
        return sysCode;
    }

    @Override
    public void modifySysCode(SysCodeBO sysCodeBO) {
        SysCode sysCode = new SysCode();
        BeanUtils.copyProperties(sysCodeBO, sysCode);
        this.baseMapper.updateById(sysCode);
        updateLocalConfig();
    }

    @Override
    public void deleteSysCode(String id) {
        this.baseMapper.deleteById(id);
        updateLocalConfig();
    }


    /**
     * 更新本地化配置
     */
    private void updateLocalConfig() {
        List<SysCode> sysCodeList = this.selectSysCodeList();
        if (!CollectionUtils.isEmpty(sysCodeList)) {

            Map<String, Map<String, String>> localConfigMap = sysCodeList.stream()
                    .collect(Collectors.groupingBy(SysCode::getCode,
                            Collectors.toMap(item -> item.getLocal().toLowerCase(), SysCode::getLang, (oldVal, newVal) -> newVal)));
            LocalConfig.setLocalConfigMap(localConfigMap);
        }
    }
}