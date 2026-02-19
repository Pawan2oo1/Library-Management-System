package com.example.library.search;

import com.example.library.model.Book;
import java.util.List;

public interface SearchStrategy {
    List<Book> search(String query);
}
