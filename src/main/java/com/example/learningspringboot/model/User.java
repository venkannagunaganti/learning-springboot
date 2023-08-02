package com.example.learningspringboot.model;

import com.example.learningspringboot.service.UserService;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Locale;
import java.util.UUID;
@AllArgsConstructor

public class User {
    private final UUID uId;
    private final String firstName;
    private final String lastName;
    private final Integer age;
    private final Gender gender;



//    public User(UUID uId, String firstName, String lastName, Integer age, Gender gender) {
//        this.uId = uId;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.age = age;
//        this.gender = gender;
//    }

    public Gender getGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getFullName(){
        return firstName+" "+lastName;
    }
    public int getDateOfBirth(){
        return LocalDate.now().minusYears(age).getYear();
    }


@JsonProperty("id")
    public UUID getuId() {
        return uId;
    }

    public enum Gender {
        MALE, FEMALE
    }


    @Override
    public String toString() {
        return "User{" +
                "uId=" + uId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
   public static User newUser(UUID uId,User user){
        return new User(uId,user.getFirstName(),user.getLastName(),user.getAge(),user.getGender());
   }


}