package com.ruswanda.blog.entity;

import com.ruswanda.blog.enumaretion.StatusRecord;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@MappedSuperclass
public class BaseEntity {


    @Enumerated(EnumType.STRING)
    @NotNull
    private StatusRecord statusRecord = StatusRecord.ACTIVE;


}
