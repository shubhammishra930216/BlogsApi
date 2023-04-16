package com.shubhammishra.blogsapi.repositories;

import com.shubhammishra.blogsapi.dto.CategoryDto;
import com.shubhammishra.blogsapi.dto.PostDto;
import com.shubhammishra.blogsapi.dto.UserDto;
import com.shubhammishra.blogsapi.entiities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {

}
