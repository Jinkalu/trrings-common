package com.triings.trringscommon.entity;

import com.triings.trringscommon.enums.TokenType;
import com.triings.trringscommon.utils.AbstractAuditEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import static jakarta.persistence.EnumType.STRING;


@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Token extends AbstractAuditEntity {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String token;

    @Column
    @Enumerated(STRING)
    private TokenType tokenType;

    @Column
    private Boolean expired;

    @Column
    private Boolean revoked;

    @Column
    private Long sessionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

}
