package com.ruswanda.blog.entity;

import com.ruswanda.blog.enumaretion.StatusRecord;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@MappedSuperclass
public class BaseEntity {

    @Id @Column(nullable = false, unique = true)
    private String id = UUID.randomUUID().toString();

    @Enumerated(EnumType.STRING)
    @NotNull
    private StatusRecord statusRecord = StatusRecord.ACTIVE;


}
