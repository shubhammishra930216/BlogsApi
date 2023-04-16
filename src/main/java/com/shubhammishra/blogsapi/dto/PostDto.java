package com.shubhammishra.blogsapi.dto;

import com.shubhammishra.blogsapi.entiities.Category;
import com.shubhammishra.blogsapi.entiities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {


    private String postId;
    private String title;
    private String content;

    private String imageName;



    private CategoryDto category;

    private UserDto user;
}
