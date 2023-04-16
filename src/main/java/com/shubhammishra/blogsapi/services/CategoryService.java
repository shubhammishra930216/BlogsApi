package com.shubhammishra.blogsapi.services;

import com.shubhammishra.blogsapi.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto,Long Id);

    List<CategoryDto> getAllCategories();

    CategoryDto getCategoryById(Long Id);
    void deleteCategory(Long Id);


}
