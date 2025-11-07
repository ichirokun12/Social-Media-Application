package com.example.Social.Media.Application.repository;

import com.example.Social.Media.Application.entity.Clubs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubRepository extends JpaRepository<Clubs, Long> {
    boolean existsByClubName(String clubName);
    List<Clubs> findByClubNameContainingIgnoreCase(String clubName);
}
