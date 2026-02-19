
package com.example.library.model;

import java.time.LocalDate;

public class Loan {
    private final String id;
    private final String copyId;
    private final String patronId;
    private final LocalDate checkoutDate;
    private final LocalDate dueDate;
    private LocalDate returnDate; // nullable

    public Loan(String id, String copyId, String patronId, LocalDate checkoutDate, LocalDate dueDate) {
        this.id = id; this.copyId = copyId; this.patronId = patronId; this.checkoutDate = checkoutDate; this.dueDate = dueDate;
    }

    public String getId() { return id; }
    public String getCopyId() { return copyId; }
    public String getPatronId() { return patronId; }
    public LocalDate getCheckoutDate() { return checkoutDate; }
    public LocalDate getDueDate() { return dueDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public boolean isActive() { return returnDate == null; }
    public void markReturned(LocalDate date) { this.returnDate = date; }

    @Override public String toString() { return "Loan{" + id + ", copy=" + copyId + ", patron=" + patronId + ", active=" + isActive() + '}'; }
}
