package com.example.Social.Media.Application.services;

import com.example.Social.Media.Application.DTO.login.RequestDTO;
import com.example.Social.Media.Application.DTO.login.ResponseDto;
import com.example.Social.Media.Application.entity.User;
import com.example.Social.Media.Application.repository.UserRepository;
import com.example.Social.Media.Application.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthUtil authUtil;

    @Autowired
    private UserRepository userRepository;

    public ResponseDto login(RequestDTO requestDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestDTO.getUsername(), requestDTO.getPassword())
            );

            // Get user from database
            User user = userRepository.findByUsername(requestDTO.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            String token = authUtil.generateAccessToken(user);

            return new ResponseDto(token, user.getUserId());
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid credentials", e);
        }
    }
}