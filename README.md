# ATM Simulation Project (Java)

## Description
This project is a Java-based ATM simulation application developed to understand secure application design and basic authentication mechanisms. The application allows users to create an account, log in using an account number and PIN, and perform basic banking operations.

The project uses Java Swing for the graphical user interface and MySQL for data storage, connected through JDBC.

---

## Features
- User registration with account number, PIN, and initial balance
- Secure login using account number and PIN
- Access control (transactions allowed only after authentication)
- Balance check with PIN re-verification
- Deposit and withdrawal with additional PIN verification
- Input validation to prevent invalid data
- Protection against SQL injection using Prepared Statements
- GUI-based navigation using buttons

---

## Technologies Used
- Java
- Java Swing (GUI)
- MySQL
- JDBC
- Git & GitHub

---

## Security Concepts Implemented
- User authentication using account number and PIN
- Access control for sensitive operations
- PIN re-verification for balance check, deposit, and withdrawal
- Input validation to prevent invalid or malicious input
- SQL injection prevention using Prepared Statements
- Secure handling of database credentials
- Client–server architecture (Java application ↔ MySQL database)

---

## How to Run

1. Compile the project:
   javac ATMGUI.java ATM.java DBConnection.java

2. Run the application:
   java -cp ".;lib/mysql-connector-j-8.2.0.jar" ATMGUI

---


## Future Improvements
- PIN hashing instead of plain storage
- Login attempt limits
- Improved GUI layout and user experience

---

## Author
Developed by Agnes Mary G