package com.example.learningspringboot.service;

import com.example.learningspringboot.dao.UserDao;
import com.example.learningspringboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers(Optional<String > gender) {
        List<User> users=userDao.selectAllUsers();
        if(!gender.isPresent()) {
            return users;
        }
        try {
          User.Gender newgender=  User.Gender.valueOf(gender.get().toUpperCase ());
            return users.stream().filter(user -> user.getGender().equals(newgender)).collect(Collectors.toList());
        }catch (Exception e){
            throw new IllegalStateException("Invalid gender",e);
        }
    }


    public Optional<User> getUser(UUID uId) {
        return userDao.selectUserByUserUid(uId);
    }


    public  int updateUser(User user) {
        Optional<User> optionalUser = getUser(user.getuId());
        if (optionalUser.isPresent()) {
            userDao.updateUser(user);
            return 1;
        }
        return -1;
    }


    public int removeUser(UUID uId) {
        Optional<User> optionalUser = getUser(uId);
        if (optionalUser.isPresent()) {
            userDao.deleteUserByUserUid(uId);
            return 1;
        }
        return-1;
    }


    public int insertUser(User user) {
UUID uid=UUID.randomUUID();

       return   userDao.insertUser(User.newUser(uid,user), uid);
    }
}
