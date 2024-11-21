package com.triings.trringscommon.repository;

import com.triings.trringscommon.entity.Users;
import com.triings.trringscommon.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByEmail(String email);

    boolean existsByUsername(String lowerCase);

    boolean existsByEmail(String lowerCase);

    boolean existsByMobile(String trim);

    Optional<Users> findByEmailAndStatus(String email, UserStatus userStatus);

    Optional<Users> findByUsernameAndStatus(String username, UserStatus userStatus);
}
