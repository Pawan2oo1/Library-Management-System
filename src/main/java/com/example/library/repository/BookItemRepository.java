package com.example.library.repository;

import com.example.library.model.BookItem;
import java.util.*;

public interface BookItemRepository {
    void save(BookItem item);
    Optional<BookItem> findByCopyId(String copyId);
    List<BookItem> findByIsbn(String isbn);
    List<BookItem> findAll();
}
