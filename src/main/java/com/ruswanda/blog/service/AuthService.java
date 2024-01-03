package com.ruswanda.blog.service;

import com.ruswanda.blog.dto.LoginDto;
import com.ruswanda.blog.dto.RegisterDto;

/**
 * Created by IntelliJ IDEA.
 * Project : blog
 * User: Ruswanda
 * Email: wandasukabumi2020@gmail.com
 * Telegram : @Ruswanda
 * Date: 20/12/23
 * Time: 09.47
 */

public interface AuthService {

    String login(LoginDto loginDto);
    String Register(RegisterDto registerDto);
}
