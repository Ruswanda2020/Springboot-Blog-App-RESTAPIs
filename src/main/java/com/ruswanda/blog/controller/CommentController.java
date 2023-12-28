package com.ruswanda.blog.controller;

import com.ruswanda.blog.dto.CommentDto;
import com.ruswanda.blog.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * Project : blog
 * User: Ruswanda
 * Email: wandasukabumi2020@gmail.com
 * Telegram : @Ruswanda
 * Date: 20/12/23
 * Time: 08.47
 */

@RestController
@RequestMapping("/api/v1/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId,
                                                    @Valid @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable long postId, @PathVariable long commentId){
        return new ResponseEntity<>(commentService.getCommentById(postId, commentId), HttpStatus.OK);
    }

    @PutMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable long postId,
                                                    @PathVariable long commentId,
                                                    @RequestBody CommentDto commentDto){
        CommentDto updateComment = commentService.updateComment(postId, commentId, commentDto);
        return new ResponseEntity<>(updateComment, HttpStatus.OK);
    }
}
