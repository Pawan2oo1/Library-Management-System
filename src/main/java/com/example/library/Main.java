
package com.example.library;

import com.example.library.model.*;
import com.example.library.repository.*;
import com.example.library.search.*;
import com.example.library.service.*;

import java.time.LocalDate;
import java.util.*;
import java.util.logging.Logger;

public class Main {
    private static final Logger log = Logger.getLogger(Main.class.getName());

    private final Scanner scanner = new Scanner(System.in);

    // repositories (in-memory)
    private final BookRepository bookRepo = new InMemoryBookRepository();
    private final BookItemRepository itemRepo = new InMemoryBookItemRepository();
    private final PatronRepository patronRepo = new InMemoryPatronRepository();
    private final LoanRepository loanRepo = new InMemoryLoanRepository();

    // search strategy factory & service
    private final SearchStrategyFactory searchFactory = new SearchStrategyFactory(bookRepo);
    private final SearchService searchService = new SearchService(searchFactory);

    // domain services
    private final BookService bookService = new BookService(bookRepo, itemRepo);
    private final PatronService patronService = new PatronService(patronRepo, loanRepo);
    private final LendingService lendingService = new LendingService(itemRepo, loanRepo);

    public static void main(String[] args) { new Main().run(); }

    private void run() {
        seed();
        while (true) {
            printMenu();
            String choice = prompt("Enter choice: ");
            try {
                switch (choice) {
                    case "1": addBook(); break;
                    case "2": updateBook(); break;
                    case "3": removeBook(); break;
                    case "4": searchBooks(); break;
                    case "5": addPatron(); break;
                    case "6": updatePatron(); break;
                    case "7": checkout(); break;
                    case "8": returnBook(); break;
                    case "9": patronHistory(); break;
                    case "0": System.out.println("Goodbye!"); return;
                    default: System.out.println("Invalid option.");
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
            System.out.println();
        }
    }

    private void printMenu() {
        System.out.println("================ Library =================");
        System.out.println("1) Add book (with copies)");
        System.out.println("2) Update book");
        System.out.println("3) Remove book");
        System.out.println("4) Search books (title/author/isbn)");
        System.out.println("5) Add patron");
        System.out.println("6) Update patron");
        System.out.println("7) Checkout a copy");
        System.out.println("8) Return a loan");
        System.out.println("9) Patron borrowing history");
        System.out.println("0) Exit");
    }

    private String prompt(String msg) {
        System.out.print(msg);
        return scanner.nextLine().trim();
    }

    private void addBook() {
        String isbn = prompt("ISBN: ");
        String title = prompt("Title: ");
        String author = prompt("Author: ");
        int year = Integer.parseInt(prompt("Publication Year: "));
        int copies = Integer.parseInt(prompt("Number of copies: "));
        bookService.addBook(new Book(isbn, title, author, year), copies);
        System.out.println("Book added.");
    }

    private void updateBook() {
        String isbn = prompt("ISBN to update: ");
        String title = prompt("New title (blank to skip): ");
        String author = prompt("New author (blank to skip): ");
        String yearStr = prompt("New year (blank to skip): ");
        Integer year = yearStr.isEmpty() ? null : Integer.parseInt(yearStr);
        bookService.updateBook(isbn, title.isEmpty()?null:title, author.isEmpty()?null:author, year);
        System.out.println("Book updated (if found).");
    }

    private void removeBook() {
        String isbn = prompt("ISBN to remove: ");
        bookService.removeBook(isbn);
        System.out.println("Book removed (metadata). Copies remain in memory for demo.");
    }

    private void searchBooks() {
        System.out.println("a) By title
b) By author
c) By ISBN");
        String opt = prompt("Choose: ");
        List<Book> results;
        switch (opt.toLowerCase()) {
            case "a": results = searchService.searchByTitle(prompt("Title contains: ")); break;
            case "b": results = searchService.searchByAuthor(prompt("Author contains: ")); break;
            case "c": results = searchService.searchByIsbn(prompt("ISBN equals: ")); break;
            default: results = Collections.emptyList();
        }
        results.forEach(System.out::println);
    }

    private void addPatron() {
        String name = prompt("Name: ");
        String email = prompt("Email: ");
        Patron p = patronService.addPatron(name, email);
        System.out.println("Patron added: " + p.getId());
    }

    private void updatePatron() {
        String id = prompt("Patron ID: ");
        String name = prompt("New name (blank to skip): ");
        String email = prompt("New email (blank to skip): ");
        patronService.updatePatron(id, name.isEmpty()?null:name, email.isEmpty()?null:email);
        System.out.println("Patron updated (if found).");
    }

    private void checkout() {
        String isbn = prompt("ISBN to checkout: ");
        Optional<BookItem> available = itemRepo.findByIsbn(isbn).stream().filter(BookItem::isAvailable).findFirst();
        if (available.isEmpty()) { System.out.println("No available copy."); return; }
        String patronId = prompt("Patron ID: ");
        var loan = lendingService.checkout(available.get().getCopyId(), patronId, LocalDate.now(), LocalDate.now().plusWeeks(2));
        System.out.println("Checked out. Loan ID: " + loan.getId());
    }

    private void returnBook() {
        String loanId = prompt("Loan ID: ");
        lendingService.returnBook(loanId, LocalDate.now());
        System.out.println("Returned.");
    }

    private void patronHistory() {
        String patronId = prompt("Patron ID: ");
        patronService.borrowingHistory(patronId).forEach(System.out::println);
    }

    private void seed() {
        // Minimal seed to try features quickly
        bookService.addBook(new Book("111", "Clean Code", "Robert C. Martin", 2008), 2);
        bookService.addBook(new Book("222", "Effective Java", "Joshua Bloch", 2018), 1);
        Patron a = patronService.addPatron("Alice", "alice@example.com");
        Patron b = patronService.addPatron("Bob", "bob@example.com");
        log.info("Seeded basic data. Patrons: " + a.getId() + ", " + b.getId());
    }
}
