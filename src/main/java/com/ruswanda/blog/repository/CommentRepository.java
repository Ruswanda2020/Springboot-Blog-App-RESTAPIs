package com.ruswanda.blog.repository;

import com.ruswanda.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

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

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostId(long id);
}
