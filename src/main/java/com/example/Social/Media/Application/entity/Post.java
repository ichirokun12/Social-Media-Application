package com.example.Social.Media.Application.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.annotations.OptimisticLock;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Jacksonized
public class Post {
    @Id
    @Column(name = "postId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId;

    @Column(name = "opinion", nullable = true)
    private String opinion;

    @Column(name = "fact", nullable = true)
    private String fact;

    @Column(name = "image", nullable = true)
    private String image;

    @Column(name = "ping", nullable = true)
    private String ping;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User assignedUser;

    @OneToMany(mappedBy = "assignPost", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference("post-comments")
    @Builder.Default
    private List<Comments> comments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    private Clubs club;


}
