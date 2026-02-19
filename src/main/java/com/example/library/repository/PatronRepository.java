package com.example.library.repository;

import com.example.library.model.Patron;
import java.util.*;

public interface PatronRepository {
    void save(Patron patron);
    Optional<Patron> findById(String id);
    List<Patron> findAll();
}
