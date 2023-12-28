package com.ruswanda.blog.service;

import com.ruswanda.blog.dto.PostDto;
import com.ruswanda.blog.dto.PostResponse;

/**
 * Created by IntelliJ IDEA.
 * Project : blog
 * User: Ruswanda
 * Email: wandasukabumi2020@gmail.com
 * Telegram : @Ruswanda
 * Date: 20/12/23
 * Time: 08.47
 */
public interface PostService {

    PostDto createPost(PostDto postDto);
    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
    PostDto getPostById(long id);
    void deletePostById(long id);
    PostDto updateById(PostDto postDto, long id);
}
