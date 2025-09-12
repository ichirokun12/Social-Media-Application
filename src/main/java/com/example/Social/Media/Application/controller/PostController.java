package com.example.Social.Media.Application.controller;

import com.example.Social.Media.Application.entity.Post;
import com.example.Social.Media.Application.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/post")
@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/getPostById/{postId}")
    public ResponseEntity<?> getPost(@PathVariable Long postId) {
        try {
            List<Post> postList = postService.getPost(postId);
            return new ResponseEntity<>(postList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("post does not exist" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody Post post) {
        try {
            Post newPost = postService.createPost(
                    post.getOpinion(),
                    post.getFact(),
                    post.getImage(),
                    post.getPing()
            );
            return new ResponseEntity<>("post is created ", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("post is not created", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/deletePost/{postId}")
    public ResponseEntity<?> deletePostById(@PathVariable Long postId) {
        try {
            List<Post> deletePost = postService.getPost(postId);
            postService.deletePost(postId);
            return new ResponseEntity<>("post has been deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("post does not exist",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
