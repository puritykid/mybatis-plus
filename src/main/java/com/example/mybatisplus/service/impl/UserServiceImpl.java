package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mybatisplus.exception.ServiceException;
import com.example.mybatisplus.mapper.MappingRoleuserMapper;
import com.example.mybatisplus.mapper.RoleMapper;
import com.example.mybatisplus.mapper.UserMapper;
import com.example.mybatisplus.pojo.MappingRoleuser;
import com.example.mybatisplus.pojo.Role;
import com.example.mybatisplus.pojo.User;
import com.example.mybatisplus.pojo.bo.UpdateUserBO;
import com.example.mybatisplus.pojo.bo.UserBO;
import com.example.mybatisplus.pojo.vo.RoleVO;
import com.example.mybatisplus.pojo.vo.UserHasRoleVO;
import com.example.mybatisplus.service.UserService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 *  用户接口实现类
 * @author haha
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MappingRoleuserMapper roleuserMapper;

    @Autowired
    private RoleMapper roleMapper;

    /**
     * @Description: 根据登录名获取用户具有的角色列表
     */
    @Override
    public List<UserHasRoleVO> queryUserList(List<String> loginNames) {

        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery()
                .select(User::getUserId,
                        User::getUserName,
                        User::getLoginName,
                        User::getTel)
                .in(User::getLoginName, loginNames);
        /**
         *  sql编写过程： select distinct .. from .. join .. on .. where .. group by .. having .. order by .. limit ..
         *  sql执行过程： from .. on .. join .. where .. group by .. having .. select distinct .. order by .. limit ..
         */
        // 1.获取用户信息
        List<User> users = userMapper.selectList(wrapper);
        // 2.获取用户的id集合
        List<String> userIds = users.stream().map(User::getUserId).filter(userId -> !StringUtils.isEmpty(userId)).distinct().collect(Collectors.toList());
        // 3.根据用户id获取用户绑定的角色
        LambdaQueryWrapper<MappingRoleuser> roleUserWrapper = Wrappers.<MappingRoleuser>lambdaQuery()
                .select(MappingRoleuser::getUserId,
                        MappingRoleuser::getRoleId)
                .in(MappingRoleuser::getUserId, userIds);
        List<MappingRoleuser> roleUsers = roleuserMapper.selectList(roleUserWrapper);
        // 4.筛选用户绑定的角色id集合
        List<String> roleIds = roleUsers.stream().map(MappingRoleuser::getRoleId).distinct().collect(Collectors.toList());
        // 5.获取角色对应的用户信息
        LambdaQueryWrapper<Role> roleWrapper = Wrappers.<Role>lambdaQuery()
                .select(Role::getRoleId,
                        Role::getRoleName)
                .in(Role::getRoleId, roleIds);

        List<Role> roles = roleMapper.selectList(roleWrapper);
        // 6.实体组装 UserHasRoleVO
        /**
         * 组装逻辑：
         * 1. 先将用户角色实体分组 key=userId,val=roleId集合
         * 2. 将角色实体集合分组
         * 3. 遍历用户集合进行数据组装
         */
        // 用户角色分组Map
        Map<String, List<String>> userRoleMap = roleUsers.stream().collect(Collectors.groupingBy(MappingRoleuser::getUserId,
                Collectors.mapping(MappingRoleuser::getRoleId, Collectors.toList())));
        // 角色分组Map
        Map<String, RoleVO> roleMap = roles.stream().collect(Collectors.toMap(Role::getRoleId,role->{
            RoleVO roleVO = new RoleVO();
            roleVO.setRoleId(role.getRoleId());
            roleVO.setRoleName(role.getRoleName());
            return roleVO;
        },(oldVal,newVal)->newVal));
        List<UserHasRoleVO> list = Lists.newArrayListWithCapacity(users.size());
        users.stream().forEach(user -> {
            // 1.拼装用户数据
            UserHasRoleVO userHasRoleVO = new UserHasRoleVO();
            userHasRoleVO.setUserId(user.getUserId());
            userHasRoleVO.setUserName(user.getUserName());
            userHasRoleVO.setLoginName(user.getLoginName());
            userHasRoleVO.setTel(user.getTel());
            // 2.拼装用户的角色数据
            List<String> roleIdList = userRoleMap.get(user.getUserId());
            List<RoleVO> roleVOS = Lists.newArrayListWithCapacity(roleIdList.size());
            roleIdList.stream().forEach(roleId->{
                RoleVO roleVO = roleMap.get(roleId);
                roleVOS.add(roleVO);
            });
            userHasRoleVO.setRoles(roleVOS);
            list.add(userHasRoleVO);
        });
        throw new ServiceException("1000");
//        return list;
    }

    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    @Override
    public User createUser(UserBO userBO) {
        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setUserName(userBO.getUserName());
        user.setLoginName(userBO.getLoginName());
        user.setLoginPass(userBO.getLoginPass());
        user.setTel(userBO.getTel());
        user.setCreatedAt(new Date());
        user.setCreatedById("001001");
        this.save(user);
        return user;
    }
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    @Override
    public void updateUser(UpdateUserBO userBO) {
        User user = new User();
        user.setUserId(userBO.getUserId());
        user.setUserName(userBO.getUserName());
        user.setLoginName(userBO.getLoginName());
        user.setTel(userBO.getTel());
        user.setUpdatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        user.setUpdatedById("001001");
        this.updateById(user);
    }
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    @Override
    public void deleteUser(String userId) {
        LambdaUpdateWrapper<User> updateWrapper = Wrappers.<User>lambdaUpdate()
                .set(User::getDeletedAt, new Date())
                .set(User::getDeletedById, userId)
                .set(User::isDeleted,true)
                .eq(User::getUserId, userId);
        this.update(updateWrapper);
    }
}
