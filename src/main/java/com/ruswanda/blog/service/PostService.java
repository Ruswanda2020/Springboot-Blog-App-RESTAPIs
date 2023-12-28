package com.ruswanda.blog.service;

import com.ruswanda.blog.dto.PostDto;
import com.ruswanda.blog.dto.PostResponseDto;

public interface PostService {

    PostDto createPost(PostDto postDto);
    PostDto findById(Long id);
    PostResponseDto findAll(int pageNo, int pageSize, String sortBy, String sortDir);
    void deleteById(Long id);
    PostDto updateById(PostDto postDto, Long id);
}
