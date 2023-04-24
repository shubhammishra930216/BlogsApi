package com.shubhammishra.blogsapi.repositories;

import com.shubhammishra.blogsapi.dto.CommentDto;
import com.shubhammishra.blogsapi.entiities.Comments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comments,Long> {

   Page<CommentDto> findByUser(Long userId, Pageable pageable);

   Page<CommentDto> findByPost(Long postId,Pageable pageable);

    Page<CommentDto> findByUserAndPost(Long userId,Long postId,Pageable pageable);
}
