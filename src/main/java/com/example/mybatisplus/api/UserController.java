package com.example.mybatisplus.api;

import com.example.mybatisplus.pojo.User;
import com.example.mybatisplus.pojo.bo.UpdateUserBO;
import com.example.mybatisplus.pojo.bo.UserBO;
import com.example.mybatisplus.pojo.vo.UserHasRoleVO;
import com.example.mybatisplus.result.Result;
import com.example.mybatisplus.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author haha
 */
@Api(value = "用户服务", tags = {"用于获取用户信息相关接口"})
@RestController
@RequestMapping("users")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "根据登录名获取用户信息", notes = "根据登录名获取用户信息", httpMethod = "GET")
    @GetMapping("/queryList")
    public Result queryList(
            @ApiParam(value = "登录名集合",name = "loginNames",required = true)
            @RequestParam("loginNames") List<String> loginNames) {
        List<UserHasRoleVO> users = userService.queryUserList(loginNames);
            return Result.ok(users);
    }


    @ApiOperation(value = "创建用户", notes = "创建用户", httpMethod = "POST")
    @PostMapping("/register")
    public Result createUser(UserBO userBO) {
        User user = userService.createUser(userBO);
        if (Objects.isNull(user)) {
            return Result.errorMsg("创建用户失败");
        }
        return Result.ok(user);
    }

    @ApiOperation(value = "修改用户", notes = "修改用户", httpMethod = "PUT")
    @PostMapping("/modifyUser")
    public Result modifyUser(UpdateUserBO userBO) {
        try {
            userService.updateUser(userBO);
            return Result.ok();
        } catch (Exception e) {
            return Result.errorException(e.getMessage());
        }
    }

    @ApiOperation(value = "删除用户", notes = "删除用户", httpMethod = "DELETE")
    @DeleteMapping("/removeUser/{id}")
    public Result removeUser(@RequestParam("userId") String userId) {
        userService.deleteUser(userId);
        return Result.ok();
    }

}
