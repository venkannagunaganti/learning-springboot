//package com.example.learningspringboot.resource;
//
//import com.example.learningspringboot.model.User;
//import com.example.learningspringboot.service.UserService;
//import jakarta.websocket.server.PathParam;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import java.util.List;
//import java.util.Optional;
//
//import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
//import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
//
//@Path("api/v1/user/")
//
//public class UserResourceRestEasy {
//    @Autowired
//    private UserService userService;
//    @GET
//    @Produces(APPLICATION_JSON)
//    public List<User> fetchUsers(@PathParam("gender") String gender) {
//
//        return userService.getAllUsers(Optional.ofNullable(gender));
//    }
//}
