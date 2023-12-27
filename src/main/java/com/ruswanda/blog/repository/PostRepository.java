package com.ruswanda.blog.repository;


import com.ruswanda.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, String> {


}
