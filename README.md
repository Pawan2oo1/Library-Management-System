
# Library Management System


## ✅ Features
- **Book Management**: add, update, remove; `Book` has title, author, ISBN, publication year
- **Search**: by **title**, **author**, or **ISBN** via **Strategy + Factory**
- **Patron Management**: add/update patrons; **borrowing history**
- **Lending**: **checkout** and **return** with short Loan IDs
- **Inventory**: track available vs borrowed copies via `BookItem`
- **Logging**: JDK `java.util.logging`

## Design
- **OOP & SOLID**: services depend on interfaces; single-responsibility services; encapsulated models
- **Patterns**: **Strategy** (search), **Factory** (search factory)

## Class Diagram (PlantUML)
![ClassD.png](ClassD.png)
