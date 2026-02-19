package com.example.library.repository;

import com.example.library.model.Book;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryBookRepository implements BookRepository {
    private final Map<String, Book> store = new ConcurrentHashMap<>();
    public void save(Book book) { store.put(book.getIsbn(), book); }
    public Optional<Book> findByIsbn(String isbn) { return Optional.ofNullable(store.get(isbn)); }
    public List<Book> findAll() { return new ArrayList<>(store.values()); }
    public void deleteByIsbn(String isbn) { store.remove(isbn); }
}
