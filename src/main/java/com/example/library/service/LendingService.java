package com.example.library.service;

import com.example.library.model.BookItem;
import com.example.library.model.Loan;
import com.example.library.repository.BookItemRepository;
import com.example.library.repository.LoanRepository;
import java.time.LocalDate;
import java.util.logging.Logger;

public class LendingService {
    private static final Logger log = Logger.getLogger(LendingService.class.getName());
    private final BookItemRepository items;
    private final LoanRepository loans;

    public LendingService(BookItemRepository items, LoanRepository loans) { this.items = items; this.loans = loans; }

    public Loan checkout(String copyId, String patronId, LocalDate checkoutDate, LocalDate dueDate) {
        BookItem item = items.findByCopyId(copyId).orElseThrow();
        if (!item.isAvailable()) throw new IllegalStateException("Copy not available");
        item.setAvailable(false); items.save(item);

        // Use short, human-friendly Loan IDs (e.g., L-001)
        String shortId = ShortIdGenerator.nextLoanId();
        Loan loan = new Loan(shortId, copyId, patronId, checkoutDate, dueDate);
        loans.save(loan);
        log.info("Checked out copy " + copyId + " to patron " + patronId);
        return loan;
    }

    public void returnBook(String loanId, LocalDate returnDate) {
        Loan loan = loans.findById(loanId).orElseThrow();
        loan.markReturned(returnDate); loans.save(loan);
        var item = items.findByCopyId(loan.getCopyId()).orElseThrow();
        item.setAvailable(true); items.save(item);
        log.info("Returned copy " + loan.getCopyId() + " from patron " + loan.getPatronId());
    }
}
