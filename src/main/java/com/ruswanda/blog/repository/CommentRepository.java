package com.ruswanda.blog.repository;

import com.ruswanda.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository extends JpaRepository<Comment, String> {



}