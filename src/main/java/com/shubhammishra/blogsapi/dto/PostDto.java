package com.shubhammishra.blogsapi.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.shubhammishra.blogsapi.entiities.Category;
import com.shubhammishra.blogsapi.entiities.Comments;
import com.shubhammishra.blogsapi.entiities.User;
import com.shubhammishra.blogsapi.view.View;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {



    private String postId;

    private String title;

    private String content;
    private String imageName;


    @JsonView(View.Category.class)
    private CategoryDto category;

    @JsonView(View.User.class)
    private UserDto user;

    private List<CommentDto> comments = new ArrayList<>();
}
