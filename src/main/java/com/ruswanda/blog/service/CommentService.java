package com.ruswanda.blog.service;


import com.ruswanda.blog.dto.CommentDto;

import java.util.List;

public interface CommentService {

    public CommentDto createComment(Long postId, CommentDto commentDto);

    List<CommentDto> getCommentByPostId(Long id);

    CommentDto getCommentById(Long postId, Long commentId);

    CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto);
    public void deleteComment(Long postId, Long commentId);
}
