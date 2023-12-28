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

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private  PostRepository postRepository;
    @Autowired
    private  ModelMapper modelMapper;

    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {

        Comment comment = mapToEntity(commentDto);

        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("post", "id", postId));

        comment.setPost(post);

        Comment newComment = commentRepository.save(comment);

        return mapToDTO(newComment);
    }

    @Override
    public List<CommentDto> getCommentByPostId(Long id) {
        List<Comment> comments = commentRepository.findByPostId(id)
                .stream().toList();
        return comments.stream().map(this::mapToDTO).toList();
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("post", "id", postId));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new ResourceNotFoundException("comment", "id", commentId));

        if (!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"comment does not belong to post");
        }
        return mapToDTO(comment);
    }

    @Override
    public CommentDto updateComment(Long postId,
                                    Long commentId,
                                    CommentDto commentRequest) {

        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("post", "id", postId));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new ResourceNotFoundException("comment", "id", commentId));

        if (!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"comment does not belong to post");
        }
        comment.setName(commentRequest.getName() == null || commentRequest.getName().isBlank() ?
                comment.getName() : commentRequest.getName());
        comment.setEmail(commentRequest.getEmail() == null || commentRequest.getEmail().isBlank() ?
                comment.getEmail() : commentRequest.getEmail());
        comment.setBody(commentRequest.getBody() == null || commentRequest.getBody().isBlank() ?
                comment.getBody() : commentRequest.getBody());
        Comment updateComment = commentRepository.save(comment);

        return mapToDTO(updateComment);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("post", "id", postId));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new ResourceNotFoundException("comment", "id", commentId));

        if (!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"comment does not belong to post");
        }

        commentRepository.delete(comment);
    }


    private CommentDto mapToDTO(Comment comment){
        return modelMapper.map(comment, CommentDto.class);
    }

    private Comment mapToEntity(CommentDto commentDto){
        return modelMapper.map(commentDto, Comment.class);
    }
}
