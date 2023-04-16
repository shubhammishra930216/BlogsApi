package com.shubhammishra.blogsapi.services;

import com.shubhammishra.blogsapi.dto.CategoryDto;
import com.shubhammishra.blogsapi.dto.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto,Long UserId,Long CategoryId);

   /**
    PostDto updatePost(PostDto postDto,Long Id);

    List<PostDto> getAllPosts();

    PostDto getPosById(Long Id);
    void deletePost(Long Id);

    List<PostDto> getPostsByCategory(Long categoryId);

    List<PostDto> getPostsByUser(Long userId);
    **/


}