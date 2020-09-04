package com.example.mybatisplus.handler;

import com.example.mybatisplus.config.LocalConfig;
import com.example.mybatisplus.exception.ServiceException;
import com.example.mybatisplus.result.Result;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

/**
 * 全局异常处理
 *
 * @Author: chenxiaojun
 * @CreateDate: 2020/9/3 16:53
 * @Version: 1.0
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest request, Exception e) {

        log.error("开始处理异常！");

        String lang = request.getHeader("LANG");
        if (StringUtils.isEmpty(lang)) {
            lang = Locale.getDefault().toString();
        }

        String msg = "";

        if (e instanceof ServiceException) {

            Map<String, String> localMap = Optional
                    .ofNullable(LocalConfig.getLocalConfigMap().get(e.getMessage()))
                    .orElse(Maps.newHashMap());
            msg = localMap.get(lang.toLowerCase());

        }else {
            msg = e.getMessage();
        }

        return Result.errorMsg(msg);
    }
}
