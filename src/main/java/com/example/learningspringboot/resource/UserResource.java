package com.example.learningspringboot.resource;

import com.example.learningspringboot.model.User;
import com.example.learningspringboot.service.UserService;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("api/v1/user/")
public class UserResource {
    @Autowired
    private UserService userService;

        @GetMapping("/")
        public List<User> fetchUsers(@PathParam("gender") String gender) {

            return userService.getAllUsers(Optional.ofNullable(gender));
        }

    @GetMapping("{uId}")
    public ResponseEntity<?> fetchUser(@PathVariable("uId") UUID uId) {
        Optional<User> user = userService.getUser(uId);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage("user  " + uId + "  not found"));
    }

    @PostMapping(path = "/adduser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> insertNewUser(@RequestBody User user) {
        int result = userService.insertUser(user);
        return getIntegerResponseEntity(result);
    }

    @PutMapping(value = "modify", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> modifyUser(@RequestBody User user) {
        int value = userService.updateUser(user);
        return getIntegerResponseEntity(value);
    }

    private ResponseEntity<Integer> getIntegerResponseEntity(int value) {
        if (value == 1) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/delete/{uId}")
    public ResponseEntity<Integer> deleteUser(@PathVariable("uId") UUID uId) {
        int result = userService.removeUser(uId);
        return getIntegerResponseEntity(result);
    }

    class ErrorMessage {
        String errorMessage;

        public ErrorMessage(String message) {
            this.errorMessage = message;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }
}
