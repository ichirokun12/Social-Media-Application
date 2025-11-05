package com.example.Social.Media.Application.services;

import com.example.Social.Media.Application.entity.Post;
import com.example.Social.Media.Application.entity.User;
import com.example.Social.Media.Application.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserServices userServices;

    public Post createPost(String opinion,
                           String fact,
                           String image,
                           String ping,
                           Long userId
                           )
    {
        Optional<User> userOptional = userServices.findById(userId);
        if(userOptional.isEmpty()) {
            throw new RuntimeException("user not found with this ID " + userId);
        }
        Post newPost = new Post();

        newPost.setOpinion(opinion);
        newPost.setFact(fact);
        newPost.setImage(image);
        newPost.setPing(ping);
        newPost.setAssignedUser(userOptional.get());


       return postRepository.save(newPost);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with ID: " + postId));
    }

    public List<Post> getPostByIdAsList(Long postId) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            return List.of(optionalPost.get());
        }
        return new ArrayList<>();
    }

    public void deletePost(Long postId) {
        if (!postRepository.existsByPostId(postId)) {
            throw new RuntimeException("user does not exists" + postId);
        }
        postRepository.deleteById(postId);
    }
}
