package com.example.library.search;

import com.example.library.repository.BookRepository;

public class SearchStrategyFactory {
    private final BookRepository repo;
    public SearchStrategyFactory(BookRepository repo) { this.repo = repo; }

    public SearchStrategy title() { return new TitleSearchStrategy(repo); }
    public SearchStrategy author() { return new AuthorSearchStrategy(repo); }
    public SearchStrategy isbn() { return new IsbnSearchStrategy(repo); }
}
