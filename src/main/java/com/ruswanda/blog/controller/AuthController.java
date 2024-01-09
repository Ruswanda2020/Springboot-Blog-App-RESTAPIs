package com.ruswanda.blog.controller;

import com.ruswanda.blog.dto.JwtAuthResponse;
import com.ruswanda.blog.dto.LoginDto;
import com.ruswanda.blog.dto.RegisterDto;
import com.ruswanda.blog.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project : blog
 * User: Ruswanda
 * Email: wandasukabumi2020@gmail.com
 * Telegram : @Ruswanda
 * Date: 20/12/23
 * Time: 09.47
 */

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@Tag(
        name = "REST APIs Authentication Resource "
)
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(value = {"/login","/signin"})
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto){

        log.info("Received a login request for user with username: {}", loginDto.getUsernameOrEmail());
        String token = authService.login(loginDto);
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        log.info("Login successful for user with username: {}", loginDto.getUsernameOrEmail());
        return ResponseEntity.ok(jwtAuthResponse);
    }

    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){

        log.info("Received a registration request for user with username: {}", registerDto.getUsername());
        String response = authService.Register(registerDto);
        log.info("Registration successful for user with username: {}", registerDto.getUsername());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
