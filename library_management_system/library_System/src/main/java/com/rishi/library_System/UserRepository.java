package com.rishi.library_System;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
	Optional<User> findByEmail(@Param("email")String email);

}
