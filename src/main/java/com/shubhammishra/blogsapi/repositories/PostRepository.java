package com.shubhammishra.blogsapi.repositories;

import com.shubhammishra.blogsapi.dto.CategoryDto;
import com.shubhammishra.blogsapi.dto.PostDto;
import com.shubhammishra.blogsapi.dto.UserDto;
import com.shubhammishra.blogsapi.entiities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<PostDto> findByCategory(CategoryDto categoryDto);
    List<PostDto> findByUser(UserDto userDto);
}
