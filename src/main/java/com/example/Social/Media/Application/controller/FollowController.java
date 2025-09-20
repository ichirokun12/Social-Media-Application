package com.example.Social.Media.Application.controller;

import com.example.Social.Media.Application.entity.User;
import com.example.Social.Media.Application.services.UserFollowerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/follow")
@RestController
public class FollowController {

    @Autowired
    private UserFollowerServices userFollowerServices;

    @PostMapping("/{followerId}/follow/{followingId}")
    public ResponseEntity<?> followUser(@PathVariable Long followerId, @PathVariable Long followingId) {
        try {
            String result = userFollowerServices.followUser(followerId, followingId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("server is down " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{followerId}/unfollow/{followingId}")
    public ResponseEntity<?> unfollowUser(@PathVariable Long followerId,@PathVariable Long followingId) {
        try {
            String result = userFollowerServices.unfollowUser(followerId, followingId);
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
