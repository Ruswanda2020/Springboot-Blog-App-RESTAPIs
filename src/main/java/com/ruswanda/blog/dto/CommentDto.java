package com.ruswanda.blog.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CommentDto {

    private String id= UUID.randomUUID().toString();
    private String name;
    private String email;
    private String body;
}
