package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.search.SearchStrategyFactory;
import java.util.List;

public class SearchService {
    private final SearchStrategyFactory factory;
    public SearchService(SearchStrategyFactory factory) { this.factory = factory; }

    public List<Book> searchByTitle(String title) { return factory.title().search(title); }
    public List<Book> searchByAuthor(String author) { return factory.author().search(author); }
    public List<Book> searchByIsbn(String isbn) { return factory.isbn().search(isbn); }
}
