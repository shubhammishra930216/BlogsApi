package com.shubhammishra.blogsapi.services.impl;

import com.shubhammishra.blogsapi.dto.CommentDto;
import com.shubhammishra.blogsapi.dto.CommentsPaginationDto;
import com.shubhammishra.blogsapi.entiities.Comments;
import com.shubhammishra.blogsapi.entiities.Post;
import com.shubhammishra.blogsapi.entiities.User;
import com.shubhammishra.blogsapi.exceptions.ResourceNotFoundException;
import com.shubhammishra.blogsapi.repositories.CommentRepository;
import com.shubhammishra.blogsapi.repositories.PostRepository;
import com.shubhammishra.blogsapi.repositories.UserRepository;
import com.shubhammishra.blogsapi.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentsServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public CommentDto createComment(CommentDto commentDto,Long userId ,Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId.toString()));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId.toString()));

        Comments comment = modelMapper.map(commentDto,Comments.class);

        comment.setPost(post);
        comment.setUser(user);
        commentRepository.save(comment);
        return modelMapper.map(comment, CommentDto.class);

    }



    @Override
    public void deleteComment(Long commentId) {

        Comments comments = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("comment", "comment_id", commentId.toString()));
        commentRepository.deleteById(commentId);

    }

    @Override
    public CommentsPaginationDto getCommentsByPost(Long postId, Integer pageNumber, Integer pageSize) {
        return null;
    }

    @Override
    public CommentsPaginationDto getCommentsByUser(Long userId, Integer pageNumber, Integer pageSize) {
        return null;
    }


}
