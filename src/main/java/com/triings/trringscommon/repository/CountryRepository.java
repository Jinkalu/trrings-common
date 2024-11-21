package com.triings.trringscommon.repository;

import com.triings.trringscommon.entity.Country;
import com.triings.trringscommon.enums.CountryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    boolean existsById(Long id);

    Optional<Country> findByIdAndStatus(Long id, CountryStatus status);
}
