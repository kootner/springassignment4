package com.sparta.springassignment4.repository;

import com.sparta.springassignment4.dto.BlogCommentListDto;
import com.sparta.springassignment4.entity.BlogComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogCommentRepository extends JpaRepository<BlogComment,Long> {
    List<BlogCommentListDto> findAllByPostIdOrderByCreatedAtDesc(Long postId);
}
