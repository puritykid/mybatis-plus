package com.example.mybatisplus.config;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.time.ZoneId;
import java.util.TimeZone;

/**
 * 统一时区配置
 * @Author: chenxiaojun
 * @CreateDate: 2020/9/3 16:07
 * @Version: 1.0
*/
@Configuration
public class TimeZoneConfig {


    /**
     * 设置默认时区
     */
    @PostConstruct
    public void timeZoneInit() {
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of("UTC")));
    }
}
