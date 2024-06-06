package org.example.repository;

import org.example.entity.RegUser;
import org.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegRepository extends JpaRepository<RegUser, Long> {
    Optional<RegUser> findByEmail(String email);
}
