package com.ruswanda.blog.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentDto {

    @NotNull
    @NotEmpty
    private Long id;
    private String name;
    private String email;
    private String body;
}
