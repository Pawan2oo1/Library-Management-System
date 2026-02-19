package com.example.library.repository;

import com.example.library.model.Loan;
import java.util.*;

public interface LoanRepository {
    void save(Loan loan);
    Optional<Loan> findById(String id);
    Optional<Loan> findActiveLoansByCopyId(String copyId);
    List<Loan> findByPatronId(String patronId);
    List<Loan> findAll();
}
