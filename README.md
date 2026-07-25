Library Management Digitalization System
# 📚 Library Record Digitization System

A desktop-based Library Record Digitization System developed using **Java Swing**, **JDBC**, and **MySQL**. The application digitizes library operations by allowing librarians to manage students, books, issue/return books, and generate reports through a user-friendly graphical interface.

---

## ✨ Features

- 🔐 Admin Login
- 👨‍🎓 Student Management
  - Add Student
  - Update Student
  - Delete Student
  - View Students
- 📖 Book Management
  - Add Book
  - Update Book
  - Delete Book
  - View Books
- 📚 Issue Books
- 🔄 Return Books
- 📊 Reports Dashboard
- 🗄 MySQL Database Integration
- 🖥 Java Swing GUI
- 📋 JTable Record Display

---

# 🛠 Technologies Used

- Java
- Java Swing
- JDBC
- MySQL
- IntelliJ IDEA
- XAMPP (MySQL Server)

---

# 📁 Project Structure

```
LibraryManagementSystem
│
├── src
│   ├── dao
│   │   ├── AdminDAO.java
│   │   ├── StudentDAO.java
│   │   ├── BookDAO.java
│   │   ├── IssueBookDAO.java
│   │   ├── ReturnBookDAO.java
│   │   └── ReportDAO.java
│   │
│   ├── database
│   │   └── DBConnection.java
│   │
│   ├── gui
│   │   ├── LoginFrame.java
│   │   ├── DashboardFrame.java
│   │   ├── StudentFrame.java
│   │   ├── BookFrame.java
│   │   ├── IssueBookFrame.java
│   │   ├── ReturnBookFrame.java
│   │   └── ReportFrame.java
│   │
│   ├── model
│   │   ├── Admin.java
│   │   ├── Student.java
│   │   ├── Book.java
│   │   └── IssueBook.java
│   │
│   ├── utils
│   │   └── Utility Classes
│   │
│   └── Main.java
│
├── .gitignore
├── LibraryManagementSystem.iml
└── README.md
```

---

# 📦 Package Description

## database

Contains the database connectivity class.

**DBConnection.java**
- Establishes connection with MySQL using JDBC.
- Provides a reusable connection throughout the project.

---

## model

Stores application data using Java classes.

Contains:

- Admin.java
- Student.java
- Book.java
- IssueBook.java

These classes use private variables with Getter and Setter methods, demonstrating **Encapsulation**.

---

## dao (Data Access Object)

Handles all database operations.

Includes:

- INSERT
- SELECT
- UPDATE
- DELETE

DAO Classes:

- AdminDAO
- StudentDAO
- BookDAO
- IssueBookDAO
- ReturnBookDAO
- ReportDAO

---

## gui

Contains all graphical user interface screens.

Includes:

- Login Screen
- Dashboard
- Student Management
- Book Management
- Issue Book
- Return Book
- Reports

Java Swing components used:

- JFrame
- JPanel
- JTable
- JButton
- JTextField
- JLabel
- JScrollPane

---

## utils

Contains helper or utility classes used across the project.

---

# 🗃 Database Structure

Database Name

```
library_db
```

Tables

### admin

```
admin_id
username
password
```

### students

```
student_id
roll_no
name
department
year
phone
email
```

### books

```
book_id
title
author
category
quantity
available
```

### issued_books

```
issue_id
student_id
book_id
issue_date
return_date
status
```

---

# ⚙️ Installation

## Step 1

Clone the repository

```bash
git clone https://github.com/YOUR_USERNAME/LibraryManagementSystem.git
```

---

## Step 2

Open the project in **IntelliJ IDEA**

---

## Step 3

Start MySQL using XAMPP.

---

## Step 4

Create a database

```sql
CREATE DATABASE library_db;
```

---

## Step 5

Import the SQL file (if provided) or manually create the required tables.

---

## Step 6

Open

```
src/database/DBConnection.java
```

Update your database credentials.

Example

```java
private static final String URL = "jdbc:mysql://localhost:3306/library_db";
private static final String USER = "root";
private static final String PASSWORD = "";
```

---

## Step 7

Run

```
Main.java
```

The Login window will open.

---

# 🔑 Default Login

If using the default admin table:

```
Username : admin
Password : admin123
```

(Change according to your database.)

---

# 🔄 Project Workflow

```
User

↓

GUI (Swing)

↓

Model

↓

DAO

↓

DBConnection

↓

MySQL Database

↓

DAO

↓

GUI

↓

User
```

---

# 📸 Application Modules

- Login
- Dashboard
- Student Management
- Book Management
- Issue Book
- Return Book
- Reports

---

# 🎯 OOP Concepts Used

- Classes & Objects
- Encapsulation
- Abstraction
- Modularity
- Reusability

---

# 👨‍💻 Developed By

**Aman Kumar**
**Yash Khapre**

Computer Engineering Student

Shah & Anchor Kutchhi Engineering College

---

# 📜 License

This project is developed for educational and learning purposes.
