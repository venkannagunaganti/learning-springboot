package com.example.learningspringboot.dao.service;

import com.example.learningspringboot.dao.FakeUserDao;
import com.example.learningspringboot.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.learningspringboot.service.UserService;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;

class UserServiceTest {
    @Mock
    private FakeUserDao fakeUserDao;
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(fakeUserDao);
    }

    @Test
    void shouldUserAllUsers() {
        UUID userid = UUID.randomUUID();
        User shilpa = new User(userid, "shilpa", "anjali", 23, User.Gender.FEMALE);

        List<User> users = userService.getAllUsers(Optional.empty());
        assertThat(users).hasSize(0);
    }

    @Test
    void shouldGetAllUsersByGender() throws Exception {
        UUID userid = UUID.randomUUID();
        User amith = new User(userid, "amith", "aditya", 25, User.Gender.MALE);
        UUID shilpauId=UUID.randomUUID();
        User shilpa=new User(shilpauId,"shilpa","anjali",23, User.Gender.FEMALE);
        fakeUserDao.insertUser(amith,userid);
        fakeUserDao.insertUser(shilpa,shilpauId);
        List<User> users=userService.getAllUsers(Optional.empty());
        given(fakeUserDao.selectAllUsers()).willReturn(users);
       List<User> filteredUsers= userService.getAllUsers(Optional.of("FEMALE"));
       assertThat(filteredUsers).hasSize(1);
    }

    @Test
    void shouldThrowException() {
        assertThatThrownBy(()->userService.getAllUsers(Optional.of("fjjdi")))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Invalid gender");
    }

    @Test
    void getUser() {
        UUID shilpaid = UUID.randomUUID();
        User shilpa = new User(shilpaid, "shilpa", "anjali", 23, User.Gender.FEMALE);
        given(fakeUserDao.selectUserByUserUid(shilpaid)).willReturn(Optional.of(shilpa));
        Optional<User> user = userService.getUser(shilpaid);
        assertThat(user.isPresent()).isTrue();


    }

    @Test
    void updateUser() {

    }

    @Test
    void removeUser() {
    }

    @Test
    void insertUser() {
    }
}