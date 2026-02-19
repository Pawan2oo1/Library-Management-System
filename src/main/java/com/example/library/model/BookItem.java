
package com.example.library.model;

import java.util.UUID;

public class BookItem {
    private final String copyId = UUID.randomUUID().toString();
    private final String isbn;
    private boolean available = true;

    public BookItem(String isbn) { this.isbn = isbn; }

    public String getCopyId() { return copyId; }
    public String getIsbn() { return isbn; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    @Override public String toString() { return "BookItem{" + copyId + ", isbn=" + isbn + ", avail=" + available + '}'; }
}
