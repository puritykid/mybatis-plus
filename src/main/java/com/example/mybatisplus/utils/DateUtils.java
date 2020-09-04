package com.example.mybatisplus.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 日期工具类
 * @Author: chenxiaojun
 * @CreateDate: 2020/9/4 15:44
 * @Version: 1.0
*/
public class DateUtils {

    private static final String DATE_PATTERN_19 = "YYYY-MM-dd HH:mm:ss";

    public static String now() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_PATTERN_19));
    }
}
