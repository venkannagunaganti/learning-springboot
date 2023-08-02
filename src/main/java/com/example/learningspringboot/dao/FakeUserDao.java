package com.example.learningspringboot.dao;

import com.example.learningspringboot.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public class FakeUserDao implements UserDao {
  private Map<UUID,User> database;
  public FakeUserDao() {
    database=new HashMap<>();
    UUID   vUId=UUID.randomUUID();
    database.put(vUId,new User(vUId,"venkanna","Gunaganti",24, User.Gender.MALE));
  }


    @Override
    public List<User> selectAllUsers() {
        return new ArrayList<>(database.values());
    }

    @Override
    public Optional<User> selectUserByUserUid(UUID uId) {

        return Optional.ofNullable(database.get(uId));
    }

    @Override
    public int updateUser(User user) {
      database.put(user.getuId(),user);
        return 1;
    }

    @Override
    public int deleteUserByUserUid(UUID uId) {
      database.remove(uId);

        return 1;
    }

    @Override
    public int insertUser(User user,UUID uId) {
      database.put(uId,user);
        return 1;
    }
}
