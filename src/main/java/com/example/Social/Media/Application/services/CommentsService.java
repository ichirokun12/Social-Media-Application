package com.example.Social.Media.Application.services;

import com.example.Social.Media.Application.entity.Comments;
import com.example.Social.Media.Application.entity.Post;
import com.example.Social.Media.Application.entity.User;
import com.example.Social.Media.Application.repository.CommentsRepository;
import com.example.Social.Media.Application.repository.PostRepository;
import com.example.Social.Media.Application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CommentsService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private UserServices userServices;

    public Comments addComment(Long userId, Long postId, String comment) {
        if(!postRepository.existsByPostId(postId)) {
            throw new RuntimeException("post does not exists with this postID " + postId);
        }

        if(!userRepository.existsByUserId(userId)) {
            throw new RuntimeException("user does not exists bu userID " + userId);
        }

        Post post = postRepository.findById(postId)
                .orElseThrow(()  -> new RuntimeException("post not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user not found"));

        Comments comments = new Comments();
        comments.setComment(comment);
        comments.setAssignPost(post);
        return commentsRepository.save(comments);
    }

    public void deleteComment(Long commentId) {

        Comments comment = commentsRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found with ID: " + commentId));

        Post post = comment.getAssignPost();

        if (post == null || !postRepository.existsById(post.getPostId())) {
            throw new RuntimeException("Post not found for this comment");
        }

        // Remove comment from post's comment list
        post.getComments().remove(comment);
        postRepository.save(post);

        // Now delete the comment
        commentsRepository.delete(comment);
    }

}
