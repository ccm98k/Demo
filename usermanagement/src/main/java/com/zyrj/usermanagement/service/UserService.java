package com.zyrj.usermanagement.service;

import com.zyrj.usermanagement.dao.UserMapper;
import com.zyrj.usermanagement.domain.User;
import com.zyrj.usermanagement.domain.sha1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther ccm
 * @date 16:40
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    @Cacheable(value = "Users")
    public List<User> findAll() {
        return userMapper.findAll();
    }

    public boolean checkName(String username) {
        return userMapper.countUser(username)==0;
    }
    @CacheEvict(value ="Users",allEntries = true)
    public void savaUser(User user) {
        userMapper.savaUser(user);
    }
    @Cacheable(value = "User",key = "#p0")
    public User findById(Integer id) {
        return userMapper.findById(id);
    }
    @CacheEvict(value ={"Users","user"},allEntries = true)
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }
    @CacheEvict(value ={"Users","user"},allEntries = true)
    public void deleteUserById(Integer id) {
        userMapper.deleteUserById(id);
    }
    @CacheEvict(value ={"Users","user"},allEntries = true)
    public void deleteUserByList(List<Integer> del_ids) {
        userMapper.deleteUserByList(del_ids);
    }
    @Cacheable(value = "User",key = "#p0")
    public User findByusername(String username) {
        System.out.println(userMapper.findByusername(username));
        return userMapper.findByusername(username);
    }
    @CacheEvict(value ={"Users","user"},allEntries = true)
    public void savePassword(User user) {

        user.setPassword(sha1.encode(user.getPassword()));

        userMapper.savePassword(user);


    }

    public void register(User user) {
        user.setPassword(sha1.encode(user.getPassword()));
        userMapper.register(user);
    }
}
