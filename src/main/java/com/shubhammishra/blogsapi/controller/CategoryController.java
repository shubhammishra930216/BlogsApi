package com.shubhammishra.blogsapi.controller;

import com.shubhammishra.blogsapi.dto.CategoryDto;
import com.shubhammishra.blogsapi.dto.UserDto;
import com.shubhammishra.blogsapi.payloads.ApiResponse;
import com.shubhammishra.blogsapi.services.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryServiceImpl categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto categoryDto1 = categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable(value = "id" ) Long id){

        CategoryDto categoryDto = categoryService.getCategoryById(id);
        return new ResponseEntity<>(categoryDto, HttpStatus.FOUND);


    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getUser(){
        List<CategoryDto> categoryDtos = categoryService.getAllCategories();
        return new ResponseEntity<>(categoryDtos, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategoryById(@Valid @RequestBody CategoryDto categoryDto,@PathVariable(value = "id" ) Long id){
        CategoryDto categoryDto1 = categoryService.updateCategory(categoryDto,id);
        return new ResponseEntity<>(categoryDto1, HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable(value = "id" ) Long id){
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(new ApiResponse("Successfully deleted category with id :"+id,true),HttpStatus.OK);

    }



}
