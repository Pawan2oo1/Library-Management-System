package com.example.library.repository;

import com.example.library.model.BookItem;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryBookItemRepository implements BookItemRepository {
    private final Map<String, BookItem> store = new ConcurrentHashMap<>();
    public void save(BookItem item) { store.put(item.getCopyId(), item); }
    public Optional<BookItem> findByCopyId(String copyId) { return Optional.ofNullable(store.get(copyId)); }
    public List<BookItem> findByIsbn(String isbn) {
        return store.values().stream().filter(i -> i.getIsbn().equals(isbn)).collect(Collectors.toList());
    }
    public List<BookItem> findAll() { return new ArrayList<>(store.values()); }
}
