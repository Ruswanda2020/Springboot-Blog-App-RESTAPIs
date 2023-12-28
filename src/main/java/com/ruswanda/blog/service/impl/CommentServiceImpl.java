package com.ruswanda.blog.service.impl;

import com.ruswanda.blog.dto.CommentDto;
import com.ruswanda.blog.entity.Comment;
import com.ruswanda.blog.entity.Post;
import com.ruswanda.blog.exception.BlogApiException;
import com.ruswanda.blog.exception.ResourceNotFoundException;
import com.ruswanda.blog.repository.CommentRepository;
import com.ruswanda.blog.repository.PostRepository;
import com.ruswanda.blog.service.CommentService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : blog
 * User: Ruswanda
 * Email: wandasukabumi2020@gmail.com
 * Telegram : @Ruswanda
 * Date: 20/12/23
 * Time: 09.47
 */

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {

        Comment comment = mapToEntity(commentDto);

        // retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));

        comment.setPost(post);
        Comment newComment = commentRepository.save(comment);
        return mapToDto(newComment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(this::mapToDto).toList();
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("post", "id", postId));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new ResourceNotFoundException("comment", "id", commentId));

        if (!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "comment does not belong to post");
        }
        return mapToDto(comment);
    }

    @Override
    public CommentDto updateComment(Long postId,
                                    long commentId,
                                    CommentDto commentRequest) {

        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("post", "id", postId));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new ResourceNotFoundException("comment", "id", commentId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belongs to post");
        }

        comment.setName(commentRequest.getName() == null || commentRequest.getName().isEmpty() ?
                comment.getName() : commentRequest.getName());
        comment.setEmail(commentRequest.getEmail() == null || commentRequest.getEmail().isEmpty() ?
                comment.getEmail() : commentRequest.getEmail());
        comment.setBody(commentRequest.getBody() == null || commentRequest.getBody().isEmpty() ?
                comment.getBody() : commentRequest.getBody());

        Comment updateComment = commentRepository.save(comment);

        return mapToDto(updateComment);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {

    }

    private CommentDto mapToDto(Comment comment){
        return modelMapper.map(comment, CommentDto.class);
    }

    private Comment mapToEntity(CommentDto commentDto){
        return modelMapper.map(commentDto, Comment.class);
    }
}
