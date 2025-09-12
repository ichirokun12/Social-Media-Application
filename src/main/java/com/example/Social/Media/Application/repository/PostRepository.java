package com.example.Social.Media.Application.repository;

import com.example.Social.Media.Application.entity.Post;
import org.apache.kafka.common.quota.ClientQuotaAlteration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    boolean existsByPostId(Long postId);

}
