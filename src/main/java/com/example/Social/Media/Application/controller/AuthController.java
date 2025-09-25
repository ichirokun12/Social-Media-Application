package com.example.Social.Media.Application.controller;

import com.example.Social.Media.Application.DTO.login.RequestDTO;
import com.example.Social.Media.Application.DTO.login.ResponseDto;
import com.example.Social.Media.Application.entity.User;
import com.example.Social.Media.Application.services.AuthService;
import com.example.Social.Media.Application.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserServices userServices;

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody User user) {
        try {
            userServices.createUser(
                    user.getUsername(),
                    user.getPassword(),
                    user.getEmail(),
                    user.getFirstName(),
                    user.getLastName()
            );
            return new ResponseEntity<>("user registration successful ",HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("not working" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@RequestBody RequestDTO requestDTO) {
        return new ResponseEntity<>(authService.login(requestDTO), HttpStatus.OK);
    }
}
