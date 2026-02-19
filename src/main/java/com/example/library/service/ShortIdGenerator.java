package com.example.library.service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Generates short, human-friendly incremental IDs for Loans.
 * Format: L-001, L-002, ... (resets each run as storage is in-memory)
 */
public final class ShortIdGenerator {
    private static final AtomicInteger COUNTER = new AtomicInteger(1);
    private ShortIdGenerator() {}
    public static String nextLoanId() {
        int n = COUNTER.getAndIncrement();
        return String.format("L-%03d", n);
        }
}
