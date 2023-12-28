package com.ruswanda.blog.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Project : blog
 * User: Ruswanda
 * Email: wandasukabumi2020@gmail.com
 * Telegram : @Ruswanda
 * Date: 20/12/23
 * Time: 08.47
 */

@Data
public class PostDto {

    private Long id ;
    @NotEmpty @NotNull
    @Size(min = 2, message = "Post title should have 2 characters")
    private String title;
    @NotEmpty @NotNull
    @Size(min = 10, message = "description should have least 10 characters")
    private String description;
    @NotEmpty @NotNull
    @Size(min = 10, message = "content should have least 10 characters")
    private String content;
    private Set<CommentDto> comments;
}