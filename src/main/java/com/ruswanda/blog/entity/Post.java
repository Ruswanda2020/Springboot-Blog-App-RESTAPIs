package com.ruswanda.blog.entity;

import com.ruswanda.blog.enumaretion.StatusRecord;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "post", uniqueConstraints =
        {@UniqueConstraint(columnNames = "title")})
@SQLDelete(sql = "UPDATE post SET status_record ='INACTIVE' WHERE post_id=?")
@Where(clause = "status_record='ACTIVE'")
public class Post {

    private String title;
    private String description;
    @Column(columnDefinition = "TEXT")
    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments;
}
