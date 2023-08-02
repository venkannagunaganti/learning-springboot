package com.example.learningspringboot.clientproxy;

import com.example.learningspringboot.model.User;
import jakarta.websocket.server.PathParam;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public interface UserResourceV1 {
    @GetMapping("/")
    public List<User> fetchUsers(@PathParam("gender") String gender) ;

    @GetMapping("{uId}")
    public ResponseEntity<?> fetchUser(@PathVariable("uId") UUID uId) ;

    @PostMapping(path = "/adduser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> insertNewUser(@RequestBody User user) ;

    @PutMapping(value = "modify", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> modifyUser(@RequestBody User user) ;



    @DeleteMapping("/delete/{uId}")
    public ResponseEntity<Integer> deleteUser(@PathVariable("uId") UUID uId) ;


}
