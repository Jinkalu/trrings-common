package com.triings.trringscommon.utils;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractAuditEntity implements Serializable {


    @Serial
    private static final long serialVersionUID = 8850003305602599251L;

    @CreatedDate
    @Column(name = "created_at",updatable = false)
    private Long createdAt = Instant.now().toEpochMilli();

    @LastModifiedDate
    @Column(name = "updated_at")
    private Long updatedAt =Instant.now().toEpochMilli();

    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;

    @LastModifiedBy
    @Column(name = "updated_by")
    private String updatedBy;


    @PrePersist
    protected void onCreate(){
        createdAt = updatedAt = Instant.now().toEpochMilli();
    }
    @PreUpdate
    protected void onUpdate(){
        updatedAt = Instant.now().toEpochMilli();
    }
}
