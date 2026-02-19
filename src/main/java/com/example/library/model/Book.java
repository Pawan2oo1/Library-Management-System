
package com.example.library.model;

import java.util.Objects;

public class Book {
    private final String isbn;
    private String title;
    private String author;
    private int publicationYear;

    public Book(String isbn, String title, String author, int publicationYear) {
        this.isbn = Objects.requireNonNull(isbn);
        this.title = Objects.requireNonNull(title);
        this.author = Objects.requireNonNull(author);
        this.publicationYear = publicationYear;
    }

    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getPublicationYear() { return publicationYear; }

    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setPublicationYear(int publicationYear) { this.publicationYear = publicationYear; }

    @Override public boolean equals(Object o) { return (o instanceof Book) && ((Book)o).isbn.equals(this.isbn); }
    @Override public int hashCode() { return java.util.Objects.hash(isbn); }
    @Override public String toString() { return String.format("Book{isbn='%s', title='%s', author='%s', year=%d}", isbn, title, author, publicationYear); }
}
