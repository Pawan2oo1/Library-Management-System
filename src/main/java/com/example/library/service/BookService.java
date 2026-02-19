package com.example.library.service;

import com.example.library.model.*;
import com.example.library.repository.*;
import java.util.Optional;
import java.util.logging.Logger;

public class BookService {
    private static final Logger log = Logger.getLogger(BookService.class.getName());
    private final BookRepository books;
    private final BookItemRepository items;

    public BookService(BookRepository books, BookItemRepository items) { this.books = books; this.items = items; }

    public void addBook(Book book, int copies) {
        books.save(book);
        for (int i = 0; i < copies; i++) items.save(new BookItem(book.getIsbn()));
        log.info("Added book " + book.getIsbn() + " with " + copies + " copies");
    }

    public void updateBook(String isbn, String title, String author, Integer year) {
        books.findByIsbn(isbn).ifPresent(b -> {
            if (title != null) b.setTitle(title);
            if (author != null) b.setAuthor(author);
            if (year != null) b.setPublicationYear(year);
            books.save(b);
        });
    }

    public void removeBook(String isbn) { books.deleteByIsbn(isbn); }

    public Optional<Book> getBook(String isbn) { return books.findByIsbn(isbn); }
}
