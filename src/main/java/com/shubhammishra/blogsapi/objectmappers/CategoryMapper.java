package com.shubhammishra.blogsapi.objectmappers;

import com.shubhammishra.blogsapi.dto.CategoryDto;
import com.shubhammishra.blogsapi.entiities.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class CategoryMapper {

    @Autowired
    private ModelMapper modelMapper;
    public Category categoryDtoToCategoryEntity(CategoryDto categoryDto){
        Category category = modelMapper.map(categoryDto,Category.class);
        return category;

    }

    public CategoryDto categoryEntityToCategoryDto(Category category){
        CategoryDto categoryDto = modelMapper.map(category,CategoryDto.class);
        return categoryDto;
    }
}
