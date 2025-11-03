package com.example.Social.Media.Application.controller;

import com.example.Social.Media.Application.entity.User;
import com.example.Social.Media.Application.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServices userServices;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable Long userId) {
       List<User> userList = userServices.getUser(userId);
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createNewUser(@RequestBody User user) {
        try {
            User newUser = userServices.createUser(
                    user.getUsername(),
                    user.getPassword(),
                    user.getEmail(),
                    user.getLastName(),
                    user.getFirstName()
            );
            return new ResponseEntity<>("user is created",HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("user isn't created"+ e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<?> deleteUserById() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            User user = userServices.findByUserName(userName)
                    .orElseThrow(() -> new RuntimeException("Authenticated user not found"));

            userServices.deleteUser(user.getUserId());
            return new ResponseEntity<>("user deleted",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("user does not exists",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
