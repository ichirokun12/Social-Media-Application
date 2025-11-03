package com.example.Social.Media.Application.controller;

import com.example.Social.Media.Application.entity.User;
import com.example.Social.Media.Application.services.UserFollowerServices;
import com.example.Social.Media.Application.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/followUser")
@RestController
public class FollowController {

    @Autowired
    private UserServices userServices;

    @Autowired
    private UserFollowerServices userFollowerServices;

    @PostMapping("/follow/{followingId}")
    public ResponseEntity<?> followUser( @PathVariable Long followingId) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            User user = userServices.findByUserName(userName)
                    .orElseThrow(() -> new RuntimeException("Authenticated user not found"));

            String result = userFollowerServices.followUser(user.getUserId(), followingId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("server is down " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/unfollow/{followingId}")
    public ResponseEntity<?> unfollowUser(@PathVariable Long followingId) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            User user = userServices.findByUserName(userName)
                    .orElseThrow(() -> new RuntimeException("Authenticated user not found"));

            String result = userFollowerServices.unfollowUser(user.getUserId(), followingId);
            return new ResponseEntity<>(result,HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{userId}/followers")
    public ResponseEntity<?> getFollowers(@PathVariable Long userId) {
        try {
            List<User> followers = userFollowerServices.getFollowers(userId);
            return new ResponseEntity<>(followers ,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("not working " + e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{userId}/following")
    public ResponseEntity<?> following(@PathVariable Long userId) {
        try {
            List<User> following = userFollowerServices.getFollowing(userId);
            return new ResponseEntity<>(following, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
