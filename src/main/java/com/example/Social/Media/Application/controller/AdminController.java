package com.example.Social.Media.Application.controller;

import com.example.Social.Media.Application.entity.User;
import com.example.Social.Media.Application.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/admin")
public class AdminController {

    @Autowired
    private UserServices userServices;

    @GetMapping("/allUsers")
    public ResponseEntity<?> getAllUser() {
        List<User> allUser = userServices.getAllUsers();

        if (allUser != null && !allUser.isEmpty()) {
            return new ResponseEntity<>(allUser, HttpStatus.OK);
        }
        return new ResponseEntity<>("users not found",HttpStatus.INTERNAL_SERVER_ERROR);
    }
}