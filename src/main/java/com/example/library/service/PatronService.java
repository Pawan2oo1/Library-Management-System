package com.example.library.service;

import com.example.library.model.Loan;
import com.example.library.model.Patron;
import com.example.library.repository.LoanRepository;
import com.example.library.repository.PatronRepository;
import java.util.List;

public class PatronService {
    private final PatronRepository patrons;
    private final LoanRepository loans;

    public PatronService(PatronRepository patrons, LoanRepository loans) { this.patrons = patrons; this.loans = loans; }

    public Patron addPatron(String name, String email) { Patron p = new Patron(name, email); patrons.save(p); return p; }

    public void updatePatron(String id, String name, String email) {
        patrons.findById(id).ifPresent(p -> { if (name != null) p.setName(name); if (email != null) p.setEmail(email); patrons.save(p); });
    }

    public List<Loan> borrowingHistory(String patronId) { return loans.findByPatronId(patronId); }
}
