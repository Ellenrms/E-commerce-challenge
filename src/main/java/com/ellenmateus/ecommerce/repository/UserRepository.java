package com.ellenmateus.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ellenmateus.ecommerce.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String name);
    Optional<User> findByEmail(String email);
    Optional<User> findByResetToken(String resetToken);
   
}
