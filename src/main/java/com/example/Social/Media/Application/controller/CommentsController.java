package com.example.Social.Media.Application.controller;

import com.example.Social.Media.Application.entity.Comments;
import com.example.Social.Media.Application.entity.Post;
import com.example.Social.Media.Application.services.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    @PostMapping("/addCommentOn/{postId}/by/{userId}")
    public ResponseEntity<?> addComment(@RequestBody Comments comments, @PathVariable Long postId, @PathVariable Long userId ) {
        try {
            Comments comments1 = commentsService.addComment(
                    userId,
                    postId,
                    comments.getComment()
            );
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("internal server error " + e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
