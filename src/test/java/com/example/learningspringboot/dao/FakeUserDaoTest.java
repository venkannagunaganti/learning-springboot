package com.example.learningspringboot.dao;

import com.example.learningspringboot.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FakeUserDaoTest {
private FakeUserDao fakeUserDao;
    @BeforeEach
    void setUp() {
        fakeUserDao=new FakeUserDao();
    }

    @Test
    void shouldSelectAllUsers() {
     List<User> users= fakeUserDao.selectAllUsers();
     assertThat(users).hasSize(1);
     User user=users.get(0);
     assertThat(user.getFirstName()).isEqualTo("venkanna");

    }

    @Test
    void shouldSelectUserByUserUid() {
        UUID ruid=UUID.randomUUID();
        User rohit=new User(ruid,"rohit","dontha",25, User.Gender.MALE);
        fakeUserDao.insertUser(rohit,ruid);
        Optional<User> rohit1=fakeUserDao.selectUserByUserUid(ruid);
        assertThat(rohit.getLastName()).isEqualTo("dontha");
assertThat(rohit1.get()).isEqualToComparingFieldByField(rohit);
    }

    @Test
    void shouldUpdateUser() {
        List<User> users=fakeUserDao.selectAllUsers();
       UUID userid=fakeUserDao.selectAllUsers().get(0).getuId();
        System.out.println(users);

        User shilpa=new User(userid,"shilpa","anjali",23, User.Gender.FEMALE);
        fakeUserDao.updateUser(shilpa);
        Optional<User> shilpa1=fakeUserDao.selectUserByUserUid(userid);
        assertThat(shilpa1.isPresent()).isTrue() ;
        assertThat(shilpa1.get().getFirstName()).isEqualTo("shilpa");
        assertThat(users).hasSize(1);
        assertThat(shilpa1.get()).isEqualToComparingFieldByField(shilpa);
    }

    @Test
    void deleteUserByUserUid() {
      UUID userId=  fakeUserDao.selectAllUsers().get(0).getuId();
      fakeUserDao.deleteUserByUserUid(userId);
      assertThat(fakeUserDao.selectUserByUserUid(userId).isPresent()).isFalse();
      assertThat((fakeUserDao.selectAllUsers())).isEmpty();
    }

    @Test
    void insertUser() {
        List<User> users=fakeUserDao.selectAllUsers();
        UUID amithid=UUID.randomUUID();
        User amith=new User(amithid,"amith","adithya",24, User.Gender.MALE);
        fakeUserDao.insertUser(amith,amithid);
        assertThat(users).hasSize(1);
        assertThat(fakeUserDao.selectUserByUserUid(amithid).get()).isEqualToComparingFieldByField(amith);

    }
}