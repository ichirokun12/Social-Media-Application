package com.example.Social.Media.Application.DTO;

import com.example.Social.Media.Application.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private Long postId;
    private String opinion;
    private String fact;
    private String image;
    private String ping;
    private UserInfo assignedUser;
    private List<CommentInfo> comments;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserInfo {
        private Long userId;
        private String username;
        private String email;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CommentInfo {
        private Long commentId;
        private String comment;
        private String author;
    }

    // Converter method - FIXED
    public static PostDTO fromEntity(Post post) {
        return PostDTO.builder()
                .postId(post.getPostId())
                .opinion(post.getOpinion())
                .fact(post.getFact())
                .image(post.getImage())
                .ping(post.getPing())
                .assignedUser(UserInfo.builder()
                        .userId(post.getAssignedUser().getUserId())
                        .username(post.getAssignedUser().getUsername())
                        .email(post.getAssignedUser().getEmail())
                        .build())
                .comments(post.getComments().stream()
                        .map(comment -> CommentInfo.builder()
                                .commentId(comment.getCommentId())
                                .comment(comment.getComment())
                                .author(comment.getAuthor() != null ? comment.getAuthor() : "Anonymous")
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}