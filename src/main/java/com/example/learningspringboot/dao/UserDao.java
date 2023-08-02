package com.example.learningspringboot.dao;

import com.example.learningspringboot.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDao {
    List<User> selectAllUsers();
    Optional<User> selectUserByUserUid(UUID uId);
    int updateUser(User user);
    int deleteUserByUserUid(UUID uId);
    int insertUser(User user,UUID uId);

}
