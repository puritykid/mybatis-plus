package com.example.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisplus.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author haha
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    public List<User> getUser();
}