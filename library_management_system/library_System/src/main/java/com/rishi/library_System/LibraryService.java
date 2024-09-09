package com.rishi.library_System;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryService {
	
	@Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    
    public List<Book> getAllBooks(){
    	return bookRepository.findAll();
    }
    
    public List<Book> findByTitle(String title){
    	return bookRepository.findByTitle(title);
    }
    
    public Author getAuthorById(int id) {
    	return authorRepository.findByIds(id);
    }
    
    public List<Book> findBooksByAuthor(int authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow(() -> 
            new IllegalArgumentException("Author not found"));
        return bookRepository.findByAuthor(author.getAuthor_id());
    }
    
    public void issueBook(int bookId, int userId) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        if (!book.isAvailable()) {
            throw new IllegalStateException("Book is not available");
        }

        User user = userRepository.findById(userId).orElseThrow();
        book.setAvailable(false);
        bookRepository.save(book);

        Transaction transaction = new Transaction();
        transaction.setBook(book);
        transaction.setUser(user);
        transactionRepository.save(transaction);
    }
    
    public void returnBook(int bookId, int userId) {
        Transaction transaction = transactionRepository.findByBookIdAndUserId(bookId, userId)
            .orElseThrow(() -> new IllegalStateException("Transaction not found"));

        Book book = transaction.getBook();
        book.setAvailable(true);
        bookRepository.save(book);

        transaction.setReturnDate(LocalDateTime.now());
        transactionRepository.save(transaction);
    }
    
    public void saveBook(Book book) {
    	bookRepository.save(book);
    }
    public void saveAuthor(Author author) {
    	authorRepository.save(author);
    }
    
    public void saveUser(User user) {
    	userRepository.save(user);
    }



}
