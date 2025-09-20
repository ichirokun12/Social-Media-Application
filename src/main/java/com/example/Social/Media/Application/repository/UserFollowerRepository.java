package com.example.Social.Media.Application.repository;

import com.example.Social.Media.Application.entity.User;
import com.example.Social.Media.Application.entity.UserFollower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserFollowerRepository extends JpaRepository<UserFollower, Long> {


    boolean existsByFollowerAndFollowing(User follower, User following);

    List<UserFollower> findByFollowing(User following);

    List<UserFollower> findByFollower(User follower);

    Optional<UserFollower> findByFollowerAndFollowing(User follower, User following);

    long countByFollowing(User following);

    long countByFollower(User follower);
}
