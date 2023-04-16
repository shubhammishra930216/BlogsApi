package com.shubhammishra.blogsapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

    private long id;

    @NotNull
    @Size(min=6,message = "Category Name cannot of size less than 6")
    private String categoryName;


    @Size(min=50,message = "Description should have at least 50 characters")
    private String description;



}
