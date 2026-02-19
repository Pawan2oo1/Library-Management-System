package com.example.library.search;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class AuthorSearchStrategy implements SearchStrategy {
    private final BookRepository repo;
    public AuthorSearchStrategy(BookRepository repo) { this.repo = repo; }
    public List<Book> search(String query) {
        String q = query.toLowerCase(Locale.ROOT);
        return repo.findAll().stream().filter(b -> b.getAuthor().toLowerCase(Locale.ROOT).contains(q)).collect(Collectors.toList());
    }
}
