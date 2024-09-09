package com.rishi.library_System;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Integer> {
	
	@Query(value = "SELECT * FROM books WHERE title = :title", nativeQuery = true)
	List<Book> findByTitle(@Param("title")String Title);
	
	 @Query(value = "SELECT * FROM books WHERE author_id = :authorId", nativeQuery = true)
	List<Book>  findByAuthor(@Param("authorId") int authorId);

}
