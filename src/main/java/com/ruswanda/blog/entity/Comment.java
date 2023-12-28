package com.ruswanda.blog.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "comment")
@SQLDelete(sql = "UPDATE comment SET status_record ='INACTIVE' WHERE id=?")
@Where(clause = "status_record='ACTIVE'")
public class Comment extends BaseEntity{

    private String name;
    private String email;
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;
}
