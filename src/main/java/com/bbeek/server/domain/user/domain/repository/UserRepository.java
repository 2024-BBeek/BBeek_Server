package com.bbeek.server.domain.user.domain.repository;

import com.bbeek.server.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByUserId(String userId);

    User findByUserId(String userId);
}
