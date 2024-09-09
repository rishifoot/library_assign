package com.rishi.library_System;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="books")
public class Book {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int book_id;
	
	private String name;
	
	
	private boolean isAvailable=true;
	
	
	@ManyToOne
	@JoinColumn(name = "author_id", nullable = false)
	private  Author author;

	public Book() {
		super();
		
	}

	public int getId() {
		return book_id;
	}

	public void setId(int id) {
		this.book_id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	

	


	
	
	

}
