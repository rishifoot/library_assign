package com.rishi.library_System;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LibraryController {
	
	@Autowired
	private LibraryService libraryService;
	
	@GetMapping
	public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = libraryService.getAllBooks();
        return ResponseEntity.ok(books);
    }


	@GetMapping("/search")
    public ResponseEntity<List<Book>> getBooksByTitle(@RequestParam String title) {
        List<Book> books = libraryService.findByTitle(title);
        if (!books.isEmpty()) {
            return new ResponseEntity<>(books, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
	
	@PostMapping("/book")
    public ResponseEntity<Void> saveBook(@RequestBody Book book) {
		Author author = libraryService.getAuthorById(book.getAuthor().getAuthor_id());
        if (author == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        book.setAuthor(author);  // Set the fetched author
        libraryService.saveBook(book);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }
	@PostMapping("/author")
    public ResponseEntity<Void> saveAuthor(@RequestBody Author author) {
        try {
            libraryService.saveAuthor(author);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }
	@PostMapping("/user")
    public ResponseEntity<Void> saveUser(@RequestBody User user) {
        try {
            libraryService.saveUser(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }
	@PostMapping("/issue")
    public ResponseEntity<Void> issueBook(@RequestParam int bookId, @RequestParam int userId) {
        try {
            libraryService.issueBook(bookId, userId);
            return new ResponseEntity<>(HttpStatus.OK); 
        } catch (Exception e) {
            
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }
	 @PostMapping("/return")
	    public ResponseEntity<Void> returnBook(@RequestParam int bookId, @RequestParam int userId) {
	        try {
	            libraryService.returnBook(bookId, userId);
	            return new ResponseEntity<>(HttpStatus.OK); 
	        } catch (Exception e) {
	            
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
	        }
	    }
	

}
