package com.example.mybatisplus.handler;

import com.example.mybatisplus.config.LocalConfig;
import com.example.mybatisplus.exception.ServiceException;
import com.example.mybatisplus.pojo.SysCode;
import com.example.mybatisplus.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

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
            lang = Locale.getDefault().getLanguage();
        }
        String msg = "";
        if (e instanceof ServiceException) {

            SysCode sysCode = LocalConfig.getLocalConfigMap().get(e.getMessage());

            String displayLanguage = Locale.US.toString();
            if (lang.equalsIgnoreCase(displayLanguage)) {
                msg = sysCode.getEnglish();
            } else {
                msg = sysCode.getChinese();
            }
        }

        return Result.errorMsg(msg);
    }
}
