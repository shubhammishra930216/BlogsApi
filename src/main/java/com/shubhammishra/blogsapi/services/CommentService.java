package com.shubhammishra.blogsapi.services;

import com.shubhammishra.blogsapi.dto.CommentDto;
import com.shubhammishra.blogsapi.dto.CommentsPaginationDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto,Long userId,Long postId);

    void deleteComment(Long commentId);

    CommentsPaginationDto getCommentsByPost(Long postId,Integer pageNumber, Integer pageSize);

    CommentsPaginationDto getCommentsByUser(Long userId,Integer pageNumber, Integer pageSize);




}
