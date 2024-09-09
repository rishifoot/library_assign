package com.rishi.library_System;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AuthorRepository extends JpaRepository<Author,Integer> {
	

    @Query(value = "SELECT * FROM authors WHERE author_id = :id", nativeQuery = true)
    Author findByIds(@Param("id")int id);

}
