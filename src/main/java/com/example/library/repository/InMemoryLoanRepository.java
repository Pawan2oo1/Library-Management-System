package com.example.library.repository;

import com.example.library.model.Loan;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryLoanRepository implements LoanRepository {
    private final Map<String, Loan> store = new ConcurrentHashMap<>();
    public void save(Loan loan) { store.put(loan.getId(), loan); }
    public Optional<Loan> findById(String id) { return Optional.ofNullable(store.get(id)); }
    public Optional<Loan> findActiveLoansByCopyId(String copyId) {
        return store.values().stream().filter(l -> l.getCopyId().equals(copyId) && l.isActive()).findFirst();
    }
    public List<Loan> findByPatronId(String patronId) {
        List<Loan> result = new ArrayList<>();
        for (Loan l : store.values()) if (l.getPatronId().equals(patronId)) result.add(l);
        return result;
    }
    public List<Loan> findAll() { return new ArrayList<>(store.values()); }
}
