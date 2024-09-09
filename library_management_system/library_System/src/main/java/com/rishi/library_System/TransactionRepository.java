package com.rishi.library_System;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
	
	@Query(value = "SELECT * FROM transactions WHERE book_id = :bookId AND user_id = :userId", nativeQuery = true)
	Optional<Transaction> findByBookIdAndUserId(@Param("bookId")int bookId, @Param("userId")int userId);

	@Query(value = "SELECT * FROM transactions WHERE user_id = :userId", nativeQuery = true)
    List<Transaction> findByUserId(@Param("userId")int userId);
    
	@Query(value = "SELECT * FROM transactions WHERE book_id = :bookId", nativeQuery = true) 
    List<Transaction> findByBookId(@Param("bookId")int bookId);

}
