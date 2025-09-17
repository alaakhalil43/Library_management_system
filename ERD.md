# Entity Relationship Diagram (ERD)

## Library Management System Database Schema

```
┌─────────────────┐
│      users      │
├─────────────────┤
│ id (PK)         │
│ username        │
│ email           │
│ password        │
│ first_name      │
│ last_name       │
│ role            │
└─────────────────┘
         │
         │ 1
         │
         │ 0..*
         │
┌─────────────────┐
│ borrowing_      │
│ transactions    │
├─────────────────┤
│ id (PK)         │
│ book_id (FK)    │
│ member_id (FK)  │
│ borrowed_by (FK)│
│ borrow_date     │
│ due_date        │
│ return_date     │
│ status          │
└─────────────────┘
         │
         │ 1
         │
         │ 0..*
         │
┌─────────────────┐
│     books       │
├─────────────────┤
│ id (PK)         │
│ title           │
│ isbn            │
│ author          │
│ publication_year│
│ total_copies    │
│ available_copies│
│ category_id (FK)│
│ publisher_id (FK)│
└─────────────────┘
         │
         │ 1
         │
         │ 0..*
         │
┌─────────────────┐
│   categories    │
├─────────────────┤
│ id (PK)         │
│ name            │
│ description     │
└─────────────────┘
         │
         │ 1
         │
         │ 0..*
         │
┌─────────────────┐
│   publishers    │
├─────────────────┤
│ id (PK)         │
│ name            │
│ description     │
└─────────────────┘
         │
         │ 1
         │
         │ 0..*
         │
┌─────────────────┐
│     members     │
├─────────────────┤
│ id (PK)         │
│ member_id       │
│ first_name      │
│ last_name       │
│ email           │
│ phone           │
└─────────────────┘
```

## Relationships

1. **users** → **borrowing_transactions** (1:many)
   - One user can have many borrowing transactions
   - Foreign key: `borrowed_by` references `users.id`

2. **books** → **borrowing_transactions** (1:many)
   - One book can have many borrowing transactions
   - Foreign key: `book_id` references `books.id`

3. **members** → **borrowing_transactions** (1:many)
   - One member can have many borrowing transactions
   - Foreign key: `member_id` references `members.id`

4. **categories** → **books** (1:many)
   - One category can have many books
   - Foreign key: `category_id` references `categories.id`

5. **publishers** → **books** (1:many)
   - One publisher can have many books
   - Foreign key: `publisher_id` references `publishers.id`

## Table Descriptions

### users
- Stores system users (admin, librarian, staff)
- Primary key: `id`
- Unique constraints: `username`, `email`

### books
- Stores library book information
- Primary key: `id`
- Unique constraint: `isbn`
- Foreign keys: `category_id`, `publisher_id`

### members
- Stores library member information
- Primary key: `id`
- Unique constraints: `member_id`, `email`

### categories
- Stores book categories/genres
- Primary key: `id`
- Unique constraint: `name`

### publishers
- Stores book publishers
- Primary key: `id`
- Unique constraint: `name`

### borrowing_transactions
- Stores book borrowing and return records
- Primary key: `id`
- Foreign keys: `book_id`, `member_id`, `borrowed_by`
- Status values: BORROWED, RETURNED, OVERDUE, LOST
