package com.ruswanda.blog.service;


import com.ruswanda.blog.dto.CommentDto;

import java.util.List;

public interface CommentService {

    public CommentDto createComment(String postId, CommentDto commentDto);

    List<CommentDto> findAllByPostId(String id);

    CommentDto getCommentById(String postId, String commentId);

    CommentDto updateComment(String postId, String commentId, CommentDto commentDto);
    public void deleteComment(String postId, String commentId);
}
