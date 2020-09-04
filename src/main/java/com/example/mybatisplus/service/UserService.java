package com.example.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mybatisplus.pojo.User;
import com.example.mybatisplus.pojo.bo.UpdateUserBO;
import com.example.mybatisplus.pojo.bo.UserBO;
import com.example.mybatisplus.pojo.vo.UserHasRoleVO;

import java.util.List;


/**
 *  用户服务接口
 * @author haha
 */
public interface UserService extends IService<User> {

    /**
     * @Description: 根据登录名查询用户具有的角色列表
     */
    List<UserHasRoleVO> queryUserList(List<String> loginNames);

    /**
     * @Description: 创建用户
     */
    User createUser(UserBO userBO);

    /**
     * @Description: 根据用户名修改用户信息
     */
    void updateUser(UpdateUserBO userBO);

    void deleteUser(String userId);
}
