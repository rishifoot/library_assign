package com.rishi.library_System;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "authors")
public class Author {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int author_id;
	
	private String name;
	
	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
	private List<Book>books;
	
	

	public Author() {
		super();
	}

	

	public int getAuthor_id() {
		return author_id;
	}



	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	
	
	

}
