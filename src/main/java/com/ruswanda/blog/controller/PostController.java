package com.ruswanda.blog.controller;

import com.ruswanda.blog.dto.PostDto;
import com.ruswanda.blog.dto.PostResponse;
import com.ruswanda.blog.service.PostService;
import com.ruswanda.blog.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
@RequestMapping("/api/v1/posts")
@Tag(
        name = "CRUD REST APIs Post Resource "
)
public class PostController {

    @Autowired
    private PostService postService;

    @Operation(
            summary = "Create Post REST API",
            description = "Create Post REST API is used to save Post into Database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http status 201 CREATED"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){

        log.info("Received a request to create a new post: {}", postDto);
        PostDto createdPost = postService.createPost(postDto);
        log.info("Post created successfully. Post details: {}", createdPost);

        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable long id ){

        log.info("Received a request to retrieve post with ID: {}", id);
        PostDto postDto = postService.getPostById(id);
        log.info("Post found for ID {}: {}", id, postDto);
        return ResponseEntity.ok(postDto);
    }

    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePostById(@RequestBody PostDto postDto, @PathVariable Long id){

        log.info("Received a request to update post with ID {}: {}", id, postDto);
        PostDto updateResponse = postService.updateById(postDto, id);
        log.info("Post with ID {} updated successfully. Updated details: {}", id, updateResponse);
        return ResponseEntity.ok(updateResponse);
    }

    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable long id){

        log.info("Received a request to delete post with ID: {}", id);
        postService.deletePostById(id);
        log.info("Post with ID {} deleted successfully.", id);

        return new ResponseEntity<>("Post entity deleted successfully.", HttpStatus.OK);
    }


    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<PostDto>> getPostByCategoryId(@PathVariable("id") Long categoryId){
        log.info("Received a request to retrieve posts for category with ID: {}", categoryId);
        List<PostDto> posts = postService.findByCategoryId(categoryId);
        log.info("Found {} posts for category with ID: {}", posts.size(), categoryId);
        return ResponseEntity.ok(posts);
    }



}
