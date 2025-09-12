package com.example.Social.Media.Application.services;

import com.example.Social.Media.Application.entity.Post;
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

    public Post createPost(String opinion,
                           String fact,
                           String image,
                           String ping
                           )
    {
        Post newPost = new Post();

        newPost.setOpinion(opinion);
        newPost.setFact(fact);
        newPost.setImage(image);
        newPost.setPing(ping);

       return postRepository.save(newPost);
    }

    public List<Post> getPost(Long postId) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        List<Post> posts = new ArrayList<>();
        optionalPost.ifPresent(posts :: add);
        return posts;
    }

    public void deletePost(Long postId) {
        if (!postRepository.existsByPostId(postId)) {
            throw new RuntimeException("user does not exists" + postId);
        }
        postRepository.deleteById(postId);
    }
}
