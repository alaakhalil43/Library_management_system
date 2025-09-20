# 📊 Database Entity Relationship Diagram (ERD)

## Library Management System Database Schema

This document provides a comprehensive overview of the database design for the Library Management System, showcasing the relationships between all entities and demonstrating proper database normalization.

## 📊 Entity Relationship Diagram

![Database ERD](ERD.png)

*Figure 1: Complete Entity Relationship Diagram showing all tables and their relationships*

### 🎯 Key Features of the ERD:
- **11 Main Tables** with proper relationships
- **Foreign Key Constraints** for data integrity
- **Many-to-Many Relationships** (Books ↔ Authors)
- **Self-Referencing Relationships** (Categories hierarchy)
- **Junction Tables** for complex relationships
- **Audit Trail** with user activity logs

## 🗄️ Database Tables Overview

### Core Entities

| Table | Description | Key Features |
|-------|-------------|--------------|
| **roles** | System user roles | 4 roles: ADMINISTRATOR, LIBRARIAN, STAFF, MEMBER |
| **users** | System users (staff) | JWT authentication, role-based access |
| **members** | Library members | Public users who borrow books |
| **books** | Book catalog | Extended metadata, multiple authors support |
| **authors** | Book authors | Many-to-many relationship with books |
| **categories** | Book categories | Hierarchical structure support |
| **publishers** | Book publishers | Complete publisher information |
| **languages** | Book languages | Multi-language support |
| **borrowing_transactions** | Borrowing records | Complete transaction tracking |
| **user_activity_logs** | Activity monitoring | Audit trail for all user actions |

## 🔗 Key Relationships

### 1. **User Management**
- **users** → **roles** (Many-to-One)
- **user_activity_logs** → **users** (Many-to-One)

### 2. **Book Management**
- **books** → **categories** (Many-to-One)
- **books** → **publishers** (Many-to-One)
- **books** → **languages** (Many-to-One)
- **books** ↔ **authors** (Many-to-Many via book_authors)

### 3. **Borrowing System**
- **borrowing_transactions** → **books** (Many-to-One)
- **borrowing_transactions** → **members** (Many-to-One)
- **borrowing_transactions** → **users** (Many-to-One)

### 4. **Hierarchical Categories**
- **categories** → **categories** (Self-referencing for parent-child)

## 📋 Table Specifications

### **roles** Table
```sql
CREATE TABLE roles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name ENUM('ADMINISTRATOR', 'LIBRARIAN', 'STAFF', 'MEMBER') NOT NULL UNIQUE
);
```

### **users** Table
```sql
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    address VARCHAR(500),
    role_id INT NOT NULL,
    FOREIGN KEY (role_id) REFERENCES roles(id)
);
```

### **members** Table
```sql
CREATE TABLE members (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(20),
    address VARCHAR(500),
    membership_number VARCHAR(50) UNIQUE,
    membership_type ENUM('REGULAR', 'PREMIUM', 'STUDENT', 'FACULTY'),
    join_date DATE
);
```

### **books** Table
```sql
CREATE TABLE books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    isbn VARCHAR(20) UNIQUE NOT NULL,
    publication_year INT,
    edition VARCHAR(50),
    summary TEXT,
    cover_image_url VARCHAR(500),
    total_copies INT DEFAULT 1,
    available_copies INT DEFAULT 1,
    category_id INT,
    publisher_id INT,
    language_id INT,
    FOREIGN KEY (category_id) REFERENCES categories(id),
    FOREIGN KEY (publisher_id) REFERENCES publishers(id),
    FOREIGN KEY (language_id) REFERENCES languages(id)
);
```

### **borrowing_transactions** Table
```sql
CREATE TABLE borrowing_transactions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    book_id INT NOT NULL,
    member_id INT NOT NULL,
    borrowed_by INT NOT NULL,
    borrow_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    due_date TIMESTAMP NOT NULL,
    return_date TIMESTAMP NULL,
    status ENUM('BORROWED', 'RETURNED', 'OVERDUE', 'LOST') DEFAULT 'BORROWED',
    FOREIGN KEY (book_id) REFERENCES books(id),
    FOREIGN KEY (member_id) REFERENCES members(id),
    FOREIGN KEY (borrowed_by) REFERENCES users(id)
);
```

## 🎯 Design Principles

### **1. Normalization**
- **3NF (Third Normal Form)** compliance
- Eliminated data redundancy
- Proper foreign key relationships

### **2. Scalability**
- Auto-incrementing primary keys
- Proper indexing on frequently queried columns
- Flexible schema for future enhancements

### **3. Data Integrity**
- Foreign key constraints
- Unique constraints on critical fields
- NOT NULL constraints where appropriate

### **4. Security**
- Encrypted password storage
- Role-based access control
- Activity logging for audit trails

## 📊 Sample Data

The database includes comprehensive sample data:

- **4 System Roles** with different permission levels
- **4 Sample Users** (one per role)
- **10 Sample Books** with complete metadata
- **5 Sample Authors** with biographies
- **3 Book Categories** with hierarchical structure
- **3 Publishers** with contact information
- **2 Languages** (English, Arabic)
- **5 Library Members** with different membership types
- **Sample Borrowing Transactions** for testing
- **User Activity Logs** for audit trails

## 🔍 Database Features

### **Indexes**
- Primary keys on all tables
- Unique indexes on username, email, ISBN
- Foreign key indexes for performance
- Composite indexes for complex queries

### **Constraints**
- **NOT NULL** constraints on required fields
- **UNIQUE** constraints on critical fields
- **FOREIGN KEY** constraints for referential integrity
- **CHECK** constraints for data validation

### **Data Types**
- Appropriate data types for each field
- VARCHAR with proper lengths
- TIMESTAMP for date/time fields
- ENUM for fixed value sets
- TEXT for large content fields

## 🚀 Performance Considerations

### **Query Optimization**
- Proper indexing strategy
- Efficient foreign key relationships
- Optimized query patterns

### **Scalability**
- Partitioning strategy for large tables
- Archiving strategy for old transactions
- Caching strategy for frequently accessed data

## 📈 Future Enhancements

The database schema is designed to support future enhancements:

- **Multi-branch library support**
- **Advanced reporting features**
- **Integration with external systems**
- **Mobile application support**
- **Advanced analytics and reporting**

---

**This ERD demonstrates a well-designed, scalable, and maintainable database schema that supports all the requirements of a modern library management system while following database design best practices.**
