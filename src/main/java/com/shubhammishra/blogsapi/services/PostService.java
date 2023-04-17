package com.shubhammishra.blogsapi.services;

import com.shubhammishra.blogsapi.dto.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto,Long UserId,Long CategoryId);


    PostDto updatePost(PostDto postDto,Long Id);


    List<PostDto> getAllPosts(Integer pageNumber, Integer pageSize);

    PostDto getPosById(Long Id);
    void deletePost(Long Id);

    List<PostDto> getPostsByCategory(Long categoryId);

    List<PostDto> getPostsByUser(Long userId);

    List<PostDto> getPostsByUserAndCategory(Long userId,Long categoryId);



}
