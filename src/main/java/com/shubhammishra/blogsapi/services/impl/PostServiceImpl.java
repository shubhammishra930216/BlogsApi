package com.shubhammishra.blogsapi.services.impl;

import com.shubhammishra.blogsapi.dto.PostDto;
import com.shubhammishra.blogsapi.entiities.Category;
import com.shubhammishra.blogsapi.entiities.Post;
import com.shubhammishra.blogsapi.entiities.User;
import com.shubhammishra.blogsapi.exceptions.ResourceNotFoundException;
import com.shubhammishra.blogsapi.repositories.CategoryRepository;
import com.shubhammishra.blogsapi.repositories.PostRepository;
import com.shubhammishra.blogsapi.repositories.UserRepository;
import com.shubhammishra.blogsapi.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    /**
     * @param postDto
     * @return
     */

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public PostDto createPost(PostDto postDto,Long userId,Long categoryId) {
        Post post = modelMapper.map(postDto,Post.class);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", categoryId));

        post.setCategory(category);
        post.setUser(user);
        post.setImageName("default.png");
        post.setCreatedAt(new Date());
        post.setUpdatedAt(new Date());
        Post savedPost = postRepository.save(post);
        return modelMapper.map(savedPost,PostDto.class);

    }

}
