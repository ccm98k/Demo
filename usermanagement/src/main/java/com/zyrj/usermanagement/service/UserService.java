package com.zyrj.usermanagement.service;

import com.zyrj.usermanagement.dao.UserMapper;
import com.zyrj.usermanagement.domain.User;
import com.zyrj.usermanagement.domain.sha1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
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

    public List<User> findAll() {
        return userMapper.findAll();
    }

    public boolean checkName(String username) {
        return userMapper.countUser(username)==0;
    }

    public void savaUser(User user) {
        userMapper.savaUser(user);
    }

    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    public void deleteUserById(Integer id) {
        userMapper.deleteUserById(id);
    }

    public void deleteUserByList(List<Integer> del_ids) {
        userMapper.deleteUserByList(del_ids);
    }

    public User findByusername(String username) {
        return userMapper.findByusername(username);
    }

    public void savePassword(User user) {

        user.setPassword(sha1.encode(user.getPassword()));

        userMapper.savePassword(user);


    }






}
