package com.shubhammishra.blogsapi.repositories;

import com.shubhammishra.blogsapi.dto.CategoryDto;
import com.shubhammishra.blogsapi.dto.PostDto;
import com.shubhammishra.blogsapi.dto.UserDto;
import com.shubhammishra.blogsapi.entiities.Category;
import com.shubhammishra.blogsapi.entiities.Post;
import com.shubhammishra.blogsapi.entiities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByCategory(Category category);
    List<Post> findByUser(User user);

    List<Post> findByUserAndCategory(User user,Category category);
}
