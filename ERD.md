# Database Entity Relationship Diagram (ERD)

## Library Management System Database Schema

### Core Entities and Relationships

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│      ROLES      │    │      USERS      │    │     MEMBERS     │
├─────────────────┤    ├─────────────────┤    ├─────────────────┤
│ id (PK)         │    │ id (PK)         │    │ id (PK)         │
│ name            │◄───┤ role_id (FK)    │    │ first_name      │
└─────────────────┘    │ username        │    │ last_name       │
                       │ email           │    │ email           │
                       │ password        │    │ phone           │
                       │ first_name      │    │ address         │
                       │ last_name       │    │ membership_no   │
                       └─────────────────┘    │ membership_type │
                                              │ join_date       │
                                              └─────────────────┘

┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│     AUTHORS     │    │      BOOKS      │    │   CATEGORIES    │
├─────────────────┤    ├─────────────────┤    ├─────────────────┤
│ id (PK)         │    │ id (PK)         │    │ id (PK)         │
│ first_name      │    │ title           │    │ name            │
│ last_name       │    │ isbn            │    │ description     │
│ biography       │    │ publication_year│    └─────────────────┘
└─────────────────┘    │ edition         │             │
         │              │ summary         │             │
         │              │ total_copies    │             │
         │              │ available_copies│             │
         │              │ category_id (FK)│◄────────────┘
         │              │ publisher_id(FK)│
         │              │ language_id(FK) │
         │              └─────────────────┘
         │                       │
         │              ┌─────────────────┐
         │              │  BOOK_AUTHORS   │
         │              ├─────────────────┤
         └─────────────►│ book_id (FK)    │
                        │ author_id (FK)  │
                        └─────────────────┘

┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   PUBLISHERS    │    │    LANGUAGES    │    │ BORROWING_TRANS │
├─────────────────┤    ├─────────────────┤    ├─────────────────┤
│ id (PK)         │    │ id (PK)         │    │ id (PK)         │
│ name            │    │ name            │    │ book_id (FK)    │
│ address         │    │ code            │    │ member_id (FK)  │
│ phone           │    └─────────────────┘    │ borrowed_by(FK) │
│ email           │                           │ borrow_date     │
│ website         │                           │ due_date        │
└─────────────────┘                           │ return_date     │
                                              │ status          │
                                              └─────────────────┘

┌─────────────────┐
│ USER_ACTIVITY   │
├─────────────────┤
│ id (PK)         │
│ user_id (FK)    │
│ action          │
│ timestamp       │
│ ip_address      │
│ user_agent      │
└─────────────────┘
```

## Entity Descriptions

### 1. **ROLES** - System Roles
- **id**: Primary key
- **name**: Role name (ADMINISTRATOR, LIBRARIAN, STAFF, MEMBER)

### 2. **USERS** - System Users
- **id**: Primary key
- **role_id**: Foreign key to ROLES
- **username**: Unique username
- **email**: Unique email address
- **password**: Encrypted password
- **first_name**: User's first name
- **last_name**: User's last name

### 3. **MEMBERS** - Library Members
- **id**: Primary key
- **first_name**: Member's first name
- **last_name**: Member's last name
- **email**: Member's email
- **phone**: Contact phone number
- **address**: Member's address
- **membership_no**: Unique membership number
- **membership_type**: Type of membership
- **join_date**: Date joined

### 4. **AUTHORS** - Book Authors
- **id**: Primary key
- **first_name**: Author's first name
- **last_name**: Author's last name
- **biography**: Author's biography

### 5. **BOOKS** - Book Catalog
- **id**: Primary key
- **title**: Book title
- **isbn**: Unique ISBN
- **publication_year**: Year of publication
- **edition**: Book edition
- **summary**: Book summary
- **total_copies**: Total number of copies
- **available_copies**: Available copies
- **category_id**: Foreign key to CATEGORIES
- **publisher_id**: Foreign key to PUBLISHERS
- **language_id**: Foreign key to LANGUAGES

### 6. **CATEGORIES** - Book Categories
- **id**: Primary key
- **name**: Category name
- **description**: Category description

### 7. **PUBLISHERS** - Book Publishers
- **id**: Primary key
- **name**: Publisher name
- **address**: Publisher address
- **phone**: Contact phone
- **email**: Contact email
- **website**: Publisher website

### 8. **LANGUAGES** - Book Languages
- **id**: Primary key
- **name**: Language name
- **code**: Language code

### 9. **BOOK_AUTHORS** - Many-to-Many Relationship
- **book_id**: Foreign key to BOOKS
- **author_id**: Foreign key to AUTHORS

### 10. **BORROWING_TRANSACTIONS** - Borrowing Records
- **id**: Primary key
- **book_id**: Foreign key to BOOKS
- **member_id**: Foreign key to MEMBERS
- **borrowed_by**: Foreign key to USERS (who processed)
- **borrow_date**: Date borrowed
- **due_date**: Due date
- **return_date**: Date returned
- **status**: Transaction status (BORROWED, RETURNED, OVERDUE, LOST)

### 11. **USER_ACTIVITY_LOGS** - Activity Tracking
- **id**: Primary key
- **user_id**: Foreign key to USERS
- **action**: Action performed
- **timestamp**: When action occurred
- **ip_address**: User's IP address
- **user_agent**: Browser information

## Key Relationships

1. **USERS → ROLES**: One-to-One (each user has one role)
2. **BOOKS → CATEGORIES**: Many-to-One (many books per category)
3. **BOOKS → PUBLISHERS**: Many-to-One (many books per publisher)
4. **BOOKS → LANGUAGES**: Many-to-One (many books per language)
5. **BOOKS ↔ AUTHORS**: Many-to-Many (books can have multiple authors)
6. **BORROWING_TRANSACTIONS → BOOKS**: Many-to-One
7. **BORROWING_TRANSACTIONS → MEMBERS**: Many-to-One
8. **BORROWING_TRANSACTIONS → USERS**: Many-to-One (who processed)
9. **USER_ACTIVITY_LOGS → USERS**: Many-to-One

## Database Features

- **Primary Keys**: Auto-incrementing integers
- **Foreign Keys**: Proper referential integrity
- **Unique Constraints**: Username, email, ISBN, membership number
- **Indexes**: On frequently queried columns
- **Data Types**: Appropriate types for each field
- **Constraints**: NOT NULL, UNIQUE, CHECK constraints where needed

## Sample Data

The database includes sample data for:
- 4 system roles
- 4 sample users (one per role)
- 5 sample members
- 10 sample books
- 5 sample authors
- 3 sample categories
- 3 sample publishers
- 2 sample languages
- Sample borrowing transactions
- Sample activity logs

This schema supports a complete library management system with proper normalization, referential integrity, and scalability for future enhancements.
