package com.zyrj.usermanagement.dao;

import com.zyrj.usermanagement.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @auther ccm
 * @date 16:41
 */
@Mapper
@Repository
public interface UserMapper {

    List<User> findAll();

    int countUser(String username);

    void savaUser(User user);

    User findById(Integer id);

    void updateUser(User user);

    void deleteUserById(Integer id);

    void deleteUserByList(List<Integer> del_ids);

    User findByusername(String username);

    void savePassword(User user);
}
