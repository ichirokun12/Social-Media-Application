package com.example.Social.Media.Application.services;

import com.example.Social.Media.Application.entity.User;
import com.example.Social.Media.Application.entity.UserFollower;
import com.example.Social.Media.Application.repository.UserFollowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.Hibernate;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserFollowerServices {

    @Autowired
    private UserFollowerRepository userFollowerRepository;

    @Autowired
    private UserServices userServices;

    public String followUser(Long followerId, Long followingId) {
        if(followerId.equals(followingId)) {
            throw new IllegalArgumentException("user can not follow themselves");
        }
        User follower = userServices.findById(followerId)
                .orElseThrow(() -> new RuntimeException("follower not found"));

        User following = userServices.findById(followingId)
                .orElseThrow(() -> new RuntimeException("User to follow not found"));

        if(userFollowerRepository.existsByFollowerAndFollowing(follower, following)) {
            return "Already following this user";
        }

        UserFollower userFollower = new UserFollower();
        userFollower.setFollower(follower);
        userFollower.setFollowing(following);
        userFollowerRepository.save(userFollower);

        updateFollowerCount(follower, following);

        return "successfully followed user";
    }

    public String unfollowUser(Long followersId, Long followingId) {

        User follower = userServices.findById(followersId)
                .orElseThrow(() -> new IllegalArgumentException("follower not found"));

        User following = userServices.findById(followingId)
                .orElseThrow(() -> new IllegalArgumentException("user to unfollow not found"));

        Optional<UserFollower> followRelation = userFollowerRepository
                .findByFollowerAndFollowing(follower, following);

       if( followRelation.isPresent()){
            userFollowerRepository.delete(followRelation.get());
            updateFollowerCount(follower, following);
            return "Successfully unfollowed user";
        }    else {
           return "you are not following this user";
       }
    }
@Transactional(readOnly = true)
    public List<User> getFollowers(Long userId) {
        User user = userServices.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

    List<User> followers = userFollowerRepository.findByFollowing(user)
            .stream()
            .map(UserFollower::getFollower)
            .collect(Collectors.toList());

    followers.forEach(Hibernate::initialize);

    return followers;
    }

    @Transactional(readOnly = true)
    public List<User> getFollowing(Long userId) {
        User user = userServices.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<User> following = userFollowerRepository.findByFollower(user)
                .stream()
                .map(UserFollower::getFollowing)
                .collect(Collectors.toList());


        following.forEach(Hibernate::initialize);

        return following;
    }

    public boolean isFollowing(Long followerId, Long followingId) {
        User follower = userServices.findById(followerId).orElse(null);
        User following = userServices.findById(followingId).orElse(null);

        if (follower == null || following == null) {
            return false;
        }

        return userFollowerRepository.existsByFollowerAndFollowing(follower, following);
    }

    public void updateFollowerCount(User follower, User following) {
        Long followingCount = userFollowerRepository.countByFollower(follower);
        follower.setFollowing(followingCount);

        Long followerCount = userFollowerRepository.countByFollowing(following);
        following.setFollowers(followerCount);

        userServices.saveUser(follower);
        userServices.saveUser(following);
    }
}
