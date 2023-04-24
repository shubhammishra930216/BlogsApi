package com.shubhammishra.blogsapi.controller;

import com.shubhammishra.blogsapi.dto.CommentDto;
import com.shubhammishra.blogsapi.payloads.ApiResponse;
import com.shubhammishra.blogsapi.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentsController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/users/{userId}/posts/{postId}/comments/")
    public ResponseEntity<CommentDto> createPost(@RequestBody CommentDto commentDto,
                                                 @PathVariable(name = "userId") Long userId,
                                                 @PathVariable(name = "postId") Long postId) {

        CommentDto commentDto1 = commentService.createComment(commentDto, userId, postId);
        return new ResponseEntity<>(commentDto1, HttpStatus.CREATED);


    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteCommentById(@PathVariable(name = "commentId") Long commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("Comment Delete Successfully with id:"+commentId, true), HttpStatus.OK);
    }
}
