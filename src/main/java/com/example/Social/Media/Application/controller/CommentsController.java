package com.example.Social.Media.Application.controller;

import com.example.Social.Media.Application.entity.Comments;
import com.example.Social.Media.Application.entity.Post;
import com.example.Social.Media.Application.entity.User;
import com.example.Social.Media.Application.services.CommentsService;
import com.example.Social.Media.Application.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentsController {

    @Autowired
    private UserServices userServices;

    @Autowired
    private CommentsService commentsService;

    @PostMapping("/addCommentOn/{postId}")
    public ResponseEntity<?> addComment(@RequestBody Comments comments, @PathVariable Long postId) {
        try {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            User user = userServices.findByUserName(userName)
                    .orElseThrow(() -> new RuntimeException("Authenticated user not found"));

            Comments comments1 = commentsService.addComment(
                    user.getUserId(),
                    postId,
                    comments.getComment()
            );
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("internal server error " + e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteComment/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId) {
        try {
             commentsService.deleteComment(commentId);
             return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
