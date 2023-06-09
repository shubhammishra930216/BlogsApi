package com.shubhammishra.blogsapi.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.shubhammishra.blogsapi.configurations.AppConstants;
import com.shubhammishra.blogsapi.dto.PostDto;
import com.shubhammishra.blogsapi.dto.PostPaginationDto;
import com.shubhammishra.blogsapi.payloads.ApiResponse;
import com.shubhammishra.blogsapi.services.impl.PostServiceImpl;
import com.shubhammishra.blogsapi.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.JsonViewRequestBodyAdvice;

import java.util.List;

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

    @GetMapping("/users/{userId}/posts/")
    @JsonView(View.User.class)
    public ResponseEntity<List<PostDto>> getPostsByUser(@RequestBody @PathVariable(name="userId") Long userId){
        List<PostDto> postDtos = postService.getPostsByUser(userId);
        return new ResponseEntity<>(postDtos, HttpStatus.OK);

    }

    @GetMapping("/category/{categoryId}/posts/")
    @JsonView(View.Category.class)
    public ResponseEntity<List<PostDto>> getPostsByCategory(@RequestBody @PathVariable(name="categoryId") Long categoryId){
        List<PostDto> postDtos = postService.getPostsByCategory(categoryId);

        return new ResponseEntity<>(postDtos, HttpStatus.OK);

    }

    @GetMapping("/users/{userId}/category/{categoryId}/posts/")
    public ResponseEntity<List<PostDto>> getPostsByUserAndCategory(@PathVariable(name="categoryId") Long categoryId,
                                                                   @PathVariable(name="userId") Long userId){
        List<PostDto> postDtos = postService.getPostsByUserAndCategory(userId,categoryId);
        return new ResponseEntity<>(postDtos, HttpStatus.OK);

    }

    @GetMapping("/posts/")
    public ResponseEntity<PostPaginationDto> getAllPosts(@RequestParam(value="pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
                                                         @RequestParam(value="pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
                                                         @RequestParam(value="sortBy" ,defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
                                                         @RequestParam(value="sortOrder" ,defaultValue = AppConstants.SORT_ORDER,required = false) String sortOrder){
        PostPaginationDto postPaginationDto = postService.getAllPosts(pageNumber,pageSize,sortBy,sortOrder);
        return new ResponseEntity<>(postPaginationDto,HttpStatus.OK);
    }

    @GetMapping("/posts/search/")
    public ResponseEntity<PostPaginationDto> getPostsContainingTitle(@RequestParam(value="title",required = true) String title,
                                                                     @RequestParam(value="pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
                                                                     @RequestParam(value="pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize){
        PostPaginationDto postPaginationDto = postService.searchPostsByTitle(title,pageNumber,pageSize);
        return new ResponseEntity<>(postPaginationDto,HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostsById(@PathVariable(name="postId") Long postId){
        PostDto postDto = postService.getPosById(postId);
        return new ResponseEntity<>(postDto,HttpStatus.FOUND);
    }

    @PostMapping("/posts/{postId}")

    public ResponseEntity<PostDto> updatePostsById(@RequestBody PostDto postDto,
                                                   @PathVariable(name="postId") Long postId){
        PostDto postDto1 = postService.updatePost(postDto,postId);
        return new ResponseEntity<>(postDto1,HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(
                                                       @PathVariable(name="postId") Long postId){
        postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("Successfully deleted post with postid :"+postId,true),HttpStatus.OK);

    }




}
