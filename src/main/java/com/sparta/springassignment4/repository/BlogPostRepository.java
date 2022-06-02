package com.sparta.springassignment4.repository;

import com.sparta.springassignment4.dto.BlogPostListDto;
import com.sparta.springassignment4.entity.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogPostRepository extends JpaRepository <BlogPost, Long> {
     List<BlogPostListDto> findAllByOrderByCreatedAtDesc();

}
