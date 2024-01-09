package com.ruswanda.blog.controller;

import com.ruswanda.blog.dto.CommentDto;
import com.ruswanda.blog.service.CommentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RestController
@RequestMapping("/api/v1/")
@Tag(
        name = "CRUD REST APIs Comment Resource "
)
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") Long postId,
                                                    @Valid @RequestBody CommentDto commentDto){

        log.info("Received a request to create a comment for post with ID {}: {}", postId, commentDto);
        CommentDto createdComment = commentService.createComment(postId, commentDto);
        log.info("Comment created successfully. Comment details: {}", createdComment);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }


    @GetMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable Long postId, @PathVariable Long commentId){

        log.info("Received a request to retrieve comment with ID {} for post with ID: {}", commentId, postId);
        CommentDto commentDto = commentService.getCommentById(postId, commentId);
        log.info("Comment found for post with ID {} and comment ID {}: {}", postId, commentId, commentDto);
        return ResponseEntity.ok(commentDto);
    }

    @PutMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Long postId,
                                                    @PathVariable Long commentId,
                                                    @RequestBody CommentDto commentDto){

        log.info("Received a request to update comment with ID {} for post with ID: {}", commentId, postId);
        CommentDto updateComment = commentService.updateComment(postId, commentId, commentDto);
        log.info("Comment with ID {} for post with ID {} updated successfully. Updated details: {}", commentId, postId, updateComment);
        return ResponseEntity.ok(updateComment);
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long postId,
                                                @PathVariable Long commentId){

        log.info("Received a request to delete comment with ID {} for post with ID: {}", commentId, postId);
        commentService.deleteComment(postId, commentId);
        log.info("Comment with ID {} for post with ID {} deleted successfully.", commentId, postId);

        return ResponseEntity.ok("Comment deleted successfully");
    }

}
