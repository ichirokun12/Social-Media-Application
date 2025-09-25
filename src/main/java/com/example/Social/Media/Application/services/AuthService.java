package com.example.Social.Media.Application.services;

import com.example.Social.Media.Application.DTO.login.RequestDTO;
import com.example.Social.Media.Application.DTO.login.ResponseDto;
import com.example.Social.Media.Application.entity.User;
import com.example.Social.Media.Application.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.time.Year;

public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthUtil authUtil;

    public ResponseDto login(RequestDTO requestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestDTO.getUsername(), requestDTO.getPassword()));
        User user = (User) authentication.getPrincipal();

        String token = authUtil.generateAccessToken(user);

        return new ResponseDto(token, user.getUserId());
    }
}
