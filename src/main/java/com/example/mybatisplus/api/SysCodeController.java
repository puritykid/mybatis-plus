package com.example.mybatisplus.api;

import com.example.mybatisplus.convert.SysConfigConverter;
import com.example.mybatisplus.pojo.SysCode;
import com.example.mybatisplus.pojo.bo.SysCodeBO;
import com.example.mybatisplus.pojo.vo.SysCodeVO;
import com.example.mybatisplus.result.Result;
import com.example.mybatisplus.service.SysCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author haha
 */
@Api(value = "国际化配置服务", tags = {"用于国际化配置服务相关接口"})
@RestController
@RequestMapping("sysConfig")
public class SysCodeController {

    @Resource
    private SysCodeService sysCodeService;


    @ApiOperation(value = "新增配置", notes = "新增配置", httpMethod = "POST")
    @PostMapping("/add")
    public Result createUser(SysCodeBO sysCodeBO) {
        SysCode sysCode = sysCodeService.createSysCode(sysCodeBO);
        if (Objects.isNull(sysCode)) {
            return Result.errorMsg("创建配置失败！");
        }

        SysCodeVO sysCodeVO = SysConfigConverter.MAPPER.doMap(sysCode);
        return Result.ok(sysCodeVO);
    }

    @ApiOperation(value = "修改配置", notes = "修改配置", httpMethod = "PUT")
    @PutMapping("/modify")
    public Result modifySysCode(SysCodeBO sysCodeBO) {
        try {
            sysCodeService.modifySysCode(sysCodeBO);
            return Result.ok();
        } catch (Exception e) {
            return Result.errorException(e.getMessage());
        }


    }

    @ApiOperation(value = "删除配置", notes = "删除配置", httpMethod = "DELETE")
    @DeleteMapping("/remove")
    public Result removeUser(@RequestParam("code") String code) {
        try {
            sysCodeService.deleteSysCode(code);
            return Result.ok();
        } catch (Exception e) {
            return Result.errorException(e.getMessage());
        }
    }

    @ApiOperation(value = "获取配置列表", notes = "获取配置列表", httpMethod = "GET")
    @GetMapping("/list")
    public Result sysConfigList() {
        try {
            List<SysCode> sysCodes = sysCodeService.selectSysCodeList();
            List<SysCodeVO> sysCodeVOList = SysConfigConverter.MAPPER.doMapList(sysCodes);
            return Result.ok(sysCodeVOList);
        } catch (Exception e) {
            return Result.errorException(e.getMessage());
        }
    }


}
