package com.example.library.repository;

import com.example.library.model.Book;
import java.util.*;

public interface BookRepository {
    void save(Book book);
    Optional<Book> findByIsbn(String isbn);
    List<Book> findAll();
    void deleteByIsbn(String isbn);
}
