package com.example.Social.Media.Application.services;

import com.example.Social.Media.Application.entity.Clubs;
import com.example.Social.Media.Application.entity.User;
import com.example.Social.Media.Application.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
public class ClubService {

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private UserServices userServices;

    public Clubs createClub(Clubs clubs, String username) {
        try {
//            if (clubRepository.ex(clubs.getName())) {
//                throw new RuntimeException("Club with this name already exists");
//            }

            User user = user = userServices.findByUserName(username)
                            .orElseThrow(() -> new RuntimeException("user not found"));
            clubs.setCreatedAt(LocalDateTime.now());
            return clubRepository.save(clubs);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Error while creating club: " + e.getMessage(), e);
        }
    }

    public List<Clubs> getAllClubs() {
        return clubRepository.findAll();
    }

    public List<Clubs> findClubByName(String clubName) {
        try {
            return clubRepository.findByClubNameContainingIgnoreCase(clubName);
        } catch (Exception e) {
            throw new RuntimeException("error " + e.getMessage());
        }
    }
}
