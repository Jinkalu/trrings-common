package com.triings.trringscommon.entity;

import com.triings.trringscommon.enums.CountryStatus;
import com.triings.trringscommon.utils.AbstractAuditEntity;
import jakarta.persistence.*;

import java.util.Set;

import lombok.*;
import lombok.experimental.SuperBuilder;

import static jakarta.persistence.EnumType.ORDINAL;


@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Country extends AbstractAuditEntity {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String countryName;

    @Column
    @Enumerated(ORDINAL)
    private CountryStatus status;

    @Column
    private String isoCode;

    @OneToMany(mappedBy = "country")
    private Set<Users> users;

}
