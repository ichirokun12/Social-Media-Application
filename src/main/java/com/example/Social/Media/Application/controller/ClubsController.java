package com.example.Social.Media.Application.controller;

import com.example.Social.Media.Application.entity.Clubs;
import com.example.Social.Media.Application.entity.User;
import com.example.Social.Media.Application.services.ClubService;
import com.example.Social.Media.Application.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clubs")
public class ClubsController {

    @Autowired
    private ClubService clubService;

    @Autowired
    private UserServices userServices;

    @PostMapping
    public ResponseEntity<?>  createClubs(@RequestBody Clubs clubs) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            User user = userServices.findByUserName(userName)
                    .orElseThrow(() -> new RuntimeException("Authenticated user not found"));

            Clubs club = clubService.createClub(clubs, user.getUsername());
            return new ResponseEntity<>(club, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllClubs")
    public ResponseEntity<?> getAllClub() {
        try {
            List<Clubs> clubs = clubService.getAllClubs();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/searchClubs")
    public ResponseEntity<List<Clubs>> searchClubs(@RequestParam String name) {
        try {
            return ResponseEntity.ok(clubService.findClubByName(name));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
