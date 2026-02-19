package com.example.library.search;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;

import java.util.List;
import java.util.stream.Collectors;

public class IsbnSearchStrategy implements SearchStrategy {
    private final BookRepository repo;
    public IsbnSearchStrategy(BookRepository repo) { this.repo = repo; }
    public List<Book> search(String query) {
        return repo.findAll().stream().filter(b -> b.getIsbn().equalsIgnoreCase(query)).collect(Collectors.toList());
    }
}
