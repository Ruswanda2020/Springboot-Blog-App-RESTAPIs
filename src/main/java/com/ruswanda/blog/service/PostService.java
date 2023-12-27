package com.ruswanda.blog.service;

import com.ruswanda.blog.dto.PostDto;
import com.ruswanda.blog.dto.PostResponseDto;
import com.ruswanda.blog.entity.Post;

public interface PostService {

    PostDto createPost(PostDto postDto);
    Post findById(String id);
    PostResponseDto findAll(int pageNo, int pageSize, String sortBy, String sortDir);
    void deleteById(String id);
    PostDto updateById(PostDto postDto, String id);
}
