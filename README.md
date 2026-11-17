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


## Project Screenshots
## Main menu
<img width="1366" height="768" alt="Screenshot (44)" src="https://github.com/user-attachments/assets/a335fbf0-939e-436d-9feb-2a3b12929709" />

## Add student
<img width="1366" height="768" alt="Screenshot (44)" src="https://github.com/user-attachments/assets/a335fbf0-939e-436d-9feb-2a3b12929709" />

## view all students
<img width="1366" height="768" alt="Screenshot (48)" src="https://github.com/user-attachments/assets/a85bb459-57e1-46fe-87f1-9931f3de10a6" />

## student dashboard
<img width="1366" height="768" alt="Screenshot (45)" src="https://github.com/user-attachments/assets/dfc264fd-c333-42ac-b4e5-315e2dba84e9" />

## CSV operations 
<img width="1366" height="768" alt="Screenshot (47)" src="https://github.com/user-attachments/assets/4270ada1-40a6-4845-a3e5-db513007aaca" />
<img width="1366" height="768" alt="Screenshot (46)" src="https://github.com/user-attachments/assets/fd2e1b41-0cda-4d2e-87e3-9d03f5c53a60" />





