package com.shubhammishra.blogsapi.services.impl;

import com.shubhammishra.blogsapi.dto.PostDto;
import com.shubhammishra.blogsapi.dto.PostPaginationDto;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId.toString()));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", categoryId.toString()));

        post.setCategory(category);
        post.setUser(user);
        post.setImageName("default.png");
        post.setCreatedAt(new Date());
        post.setUpdatedAt(new Date());
        Post savedPost = postRepository.save(post);
        return modelMapper.map(savedPost,PostDto.class);

    }

    @Override
    @Transactional
    public PostDto updatePost(PostDto postDto, Long Id) {
        Post post = postRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("post", "post_id", Id.toString()));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        Post savedPost  = postRepository.save(post);
        return modelMapper.map(savedPost,PostDto.class);
    }

    @Override
    public PostPaginationDto getAllPosts(Integer pageNumber, Integer pageSize,String sortBy,String sortOrder) {
        Sort sort = null;

        if (sortOrder.equalsIgnoreCase("desc")){
            sort =Sort.by(sortBy).descending();
        } else if (sortOrder.equalsIgnoreCase("asc")) {
            sort = Sort.by(sortBy).ascending();
        }
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);
        Page<Post> postPage = postRepository.findAll(pageable);
        List<Post> post = postPage.getContent();

        List<PostDto> postDtos = post.stream().map(post1 -> modelMapper.map(post1,PostDto.class)).collect(Collectors.toList());

        PostPaginationDto postPaginationDto = new PostPaginationDto();
        postPaginationDto.setContent(postDtos);
        postPaginationDto.setPageNumber(postPage.getNumber());
        postPaginationDto.setPageSize(postPage.getSize());
        postPaginationDto.setTotalPages(postPage.getTotalPages());
        postPaginationDto.setTotalElements(postPage.getTotalElements());
        postPaginationDto.setLastPage(postPage.isLast());
        return postPaginationDto;
    }


    @Override
    public PostDto getPosById(Long Id) {
        Post post = postRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("post", "post_id", Id.toString()));

        return modelMapper.map(post,PostDto.class);
    }

    @Override
    public void deletePost(Long Id) {
        Post post = postRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("post", "post_id", Id.toString()));
        postRepository.deleteById(Id);

    }

    @Override
    public List<PostDto> getPostsByCategory(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", categoryId.toString()));
        List<Post> post = postRepository.findByCategory(category);
        List<PostDto> postDtos = post.stream().map(post1 -> modelMapper.map(post1,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId.toString()));
        List<Post> post1 = postRepository.findByUser(user);
        List<PostDto> postDtos = post1.stream().map(post -> modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostsByUserAndCategory(Long userId, Long categoryId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId.toString()));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", categoryId.toString()));
        List<Post> post1 = postRepository.findByUserAndCategory(user,category);
        List<PostDto> postDtos = post1.stream().map(post -> modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;

    }



    @Override
    public PostPaginationDto searchPostsByTitle(String Keyword, Integer pageNumber, Integer pageSize) {

        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        Page<Post> postPage = postRepository.findByTitleContaining(Keyword,pageable);
        List<Post> post = postPage.getContent();

        List<PostDto> postDtos = post.stream().map(post1 -> modelMapper.map(post1,PostDto.class)).collect(Collectors.toList());

        PostPaginationDto postPaginationDto = new PostPaginationDto();
        postPaginationDto.setContent(postDtos);
        postPaginationDto.setPageNumber(postPage.getNumber());
        postPaginationDto.setPageSize(postPage.getSize());
        postPaginationDto.setTotalPages(postPage.getTotalPages());
        postPaginationDto.setTotalElements(postPage.getTotalElements());
        postPaginationDto.setLastPage(postPage.isLast());
        return postPaginationDto;
    }

    @Override
    public PostPaginationDto searchPostsByContent(String Keyword) {
        return null;
    }

}
