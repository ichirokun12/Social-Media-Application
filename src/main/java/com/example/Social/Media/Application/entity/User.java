package com.example.Social.Media.Application.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

//    user id
    @Id
    @Column(name = "userIs")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

//    username
    @Column(name = "username", nullable = false, unique = true)
    private String username;

//    password
    @Column(name = "password", nullable = false)
    private String password;

//    email
    @Column(name = "email", nullable = false, unique = true)
    private String email;

//    firstname
    @Column(name = "firstName")
    private String firstName;

//    lastName
    @Column(name = "lastName")
    private String lastName;

    private Long followers;
    private Long following;



}
