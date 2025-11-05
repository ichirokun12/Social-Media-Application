package com.example.Social.Media.Application.services;

import com.example.Social.Media.Application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.example.Social.Media.Application.entity.User;


@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public User createUser(
            String username,
            String password,
            String email,
            String firstName,
            String lastName

    )

    {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.getRoles().add("USER");

        return userRepository.save(user);
    }

    public List<User> getUser(Long userId) {
        Optional<User> optionalUser = userRepository.findByUserId(userId);
        List<User> users = new ArrayList<>();
        optionalUser.ifPresent(users :: add);
        return users;
    }

    public void deleteUser(Long userId) {
        if(!userRepository.existsByUserId(userId)) {
            throw new RuntimeException("user does not exists" + userId);
        }
        userRepository.deleteById(userId);
    }

    public Optional<User> findById(Long userId) {
       return userRepository.findById(userId);
    }

//    public Optional<User> getAllUser(String username) {
//        return userRepository.findAll(username);
//    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

//    public Optional<User> existByUsername(String username) {
//        return userRepository.existByUsername(username);
//    }

    public Optional<User> findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }


    public List<User> getAllUsers() {
       return userRepository.findAll();
    }

}
