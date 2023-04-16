package com.shubhammishra.blogsapi.controller;

import com.shubhammishra.blogsapi.dto.PostDto;
import com.shubhammishra.blogsapi.services.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostServiceImpl postService;

    @PostMapping("/users/{userId}/category/{categoryId}/posts/")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable(name = "userId") Long userId,
                                              @PathVariable(name = "categoryId") Long categoryId){

        PostDto postDto1 = postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<>(postDto1, HttpStatus.CREATED);

    }

}
