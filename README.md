# Student Management System

A console-based Student Management System developed using **Java and MySQL** with JDBC connectivity.

## Project Overview

This application helps manage student records efficiently through a menu-driven console interface. It supports student registration, searching, updating, deleting, sorting, dashboard statistics, and CSV file operations.

## Features

- Add new student records
- View all student records
- Search students by ID and Name
- Update student information
- Delete student records with confirmation
- Input validation and exception handling
- Student dashboard with statistics
- Sort records by ID, Name, and Age
- Export student records to CSV
- Import student records from CSV
- Prevent duplicate email registration

## Technologies Used

- Java
- MySQL
- JDBC

## Project Structure

```text
StudentManagementSystem/
│
├── Main.java
├── DBConnection.java
├── StudentDAO.java
├── StudentValidator.java
└── README.md
```

## Database Configuration

1. Install MySQL and create a database named `student_db`.
2. Create a table named `students` with the required columns:
   - id
   - name
   - age
   - department
   - email
3. Configure your MySQL connection in `DBConnection.java`.
4. Add MySQL Connector/J to your Java project.

**Security:** Do not publish your database password or other private credentials.

## How to Run

1. Clone or download this repository.
2. Open the project in VS Code.
3. Configure the MySQL database.
4. Add the MySQL JDBC driver.
5. Compile and run `Main.java`.

## Learning Outcomes

Through this project, I gained practical experience in:

- Core Java and OOP concepts
- JDBC connectivity
- MySQL database operations
- CRUD operations
- Exception handling and input validation
- CSV file handling


