package com.example.mybatisplus.config;

import com.example.mybatisplus.pojo.SysCode;
import com.example.mybatisplus.service.SysCodeService;
import com.google.common.collect.Maps;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 初始化国际化配置
 *
 * @Author: chenxiaojun
 * @CreateDate: 2020/9/3 16:32
 * @Version: 1.0
 */
@Configuration
public class LocalConfig {

    @Resource
    private SysCodeService sysCodeService;

    /**
     * 定义一个配置容器
     */
    private static Map<String, Map<String,String>> localConfigMap =Maps.newHashMap();

    @PostConstruct
    public void initLocalConfig() {
        List<SysCode> sysCodeList = sysCodeService.selectSysCodeList();
        if (!CollectionUtils.isEmpty(sysCodeList)) {
            localConfigMap = sysCodeList.stream()
                    .collect(Collectors.groupingBy(SysCode::getCode,
                            Collectors.toMap(item->item.getLocal().toLowerCase(),SysCode::getLang, (oldVal, newVal) -> newVal)));
        }
    }

    public static Map<String,  Map<String,String>> getLocalConfigMap() {
        return localConfigMap==null?Maps.newHashMap():localConfigMap;
    }

    public static void setLocalConfigMap(Map<String,  Map<String,String>> localConfigMap) {
        LocalConfig.localConfigMap = localConfigMap;
    }
}

