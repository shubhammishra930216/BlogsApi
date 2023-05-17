package com.shubhammishra.blogsapi.services.impl;

import com.shubhammishra.blogsapi.dto.CategoryDto;
import com.shubhammishra.blogsapi.entiities.Category;
import com.shubhammishra.blogsapi.exceptions.ResourceNotFoundException;
import com.shubhammishra.blogsapi.objectmappers.CategoryMapper;
import com.shubhammishra.blogsapi.repositories.CategoryRepository;
import com.shubhammishra.blogsapi.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryRepository categoryRepository;


    /**
     * @param categoryDto
     * @return
     */
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = categoryMapper.categoryDtoToCategoryEntity(categoryDto);
        Category saveCategory = categoryRepository.save(category);
        return categoryMapper.categoryEntityToCategoryDto(saveCategory);
    }

    /**
     * @param categoryDto
     * @param Id
     * @return
     */
    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long Id) {
        Category category = categoryRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", Id.toString()));
        //categoryDto.setId(category.getId());
        category.setCategoryName(categoryDto.getCategoryName());
        category.setDescription(categoryDto.getDescription());
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.categoryEntityToCategoryDto(savedCategory);
    }

    /**
     * @return
     */
    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtos = categories.stream().map(category -> categoryMapper.categoryEntityToCategoryDto(category)).collect(Collectors.toList());
        return categoryDtos;
    }

    /**
     * @param Id
     * @return
     */
    @Override
    public CategoryDto getCategoryById(Long Id) {
        Category category = categoryRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", Id.toString()));

        CategoryDto categoryDto = categoryMapper.categoryEntityToCategoryDto(category);

        return categoryDto;
    }

    /**
     * @param Id
     */
    @Override
    public void deleteCategory(Long Id) {
        Category category = categoryRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", Id.toString()));
        categoryRepository.deleteById(Id);

    }
}
