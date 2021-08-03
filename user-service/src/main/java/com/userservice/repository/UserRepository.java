package com.userservice.repository;

import com.userservice.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(String userId);
    User findByEmail(String email);
}
