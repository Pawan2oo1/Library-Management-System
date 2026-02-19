package com.example.library.search;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class TitleSearchStrategy implements SearchStrategy {
    private final BookRepository repo;
    public TitleSearchStrategy(BookRepository repo) { this.repo = repo; }
    public List<Book> search(String query) {
        String q = query.toLowerCase(Locale.ROOT);
        return repo.findAll().stream().filter(b -> b.getTitle().toLowerCase(Locale.ROOT).contains(q)).collect(Collectors.toList());
    }
}
