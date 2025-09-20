# 📚 Library Management System - Updated API Documentation

## 🎯 Task Requirements Coverage

This document covers all the required RESTful API endpoints as specified in the task:

### ✅ **Required APIs:**
- **Book Management** - Complete CRUD operations
- **Member Management** - Complete CRUD operations  
- **System User Management** - With different permission levels
- **Borrowing and Return Functionality** - Complete transaction management

### ✅ **Bonus Features:**
- **Postman Collection** - Complete organized collection
- **User Activity Logging** - Security and monitoring
- **Author Management** - Extended book metadata
- **Supporting Entities** - Categories, Publishers, Languages, Roles

---

## 🔗 Base URL
```
http://localhost:8091
```

---

## 👥 User Roles & Permissions

| Role | Description | What They Can Do |
|------|-------------|------------------|
| **MEMBER** | Library members | • View books and authors<br>• Search catalog |
| **STAFF** | Library staff | • View all data (read-only)<br>• Cannot create/update/delete |
| **LIBRARIAN** | Senior staff | • Manage books and authors<br>• Manage members and borrowing<br>• View activity logs |
| **ADMINISTRATOR** | System admin | • Full system access<br>• Manage all users and roles<br>• Delete anything |

---

## 🔐 1. Authentication APIs

### **User Authentication & Session Management**

| Method | Endpoint | Description | Permission |
|--------|----------|-------------|------------|
| `POST` | `/api/auth/login` | User login | Public |
| `POST` | `/api/auth/logout` | User logout | Authenticated |
| `GET` | `/api/auth/me` | Get current user info | Authenticated |
| `GET` | `/api/auth/status` | Check auth status | Public |
| `POST` | `/api/users/register` | Public user registration (defaults to MEMBER) | Public |
| `GET` | `/api/users/available-roles` | Get available roles for registration | Public |

### **Login Request Body:**
```json
{
  "username": "admin",
  "password": "admin123"
}
```

### **Login Response:**
```json
{
  "success": true,
  "message": "Login successful",
  "user": {
    "id": 1,
    "username": "admin",
    "email": "admin@library.com",
    "firstName": "Admin",
    "lastName": "User",
    "role": {
      "id": 1,
      "name": "ADMINISTRATOR"
    }
  }
}
```

### **Register Request Body:**
```json
{
  "username": "newuser",
  "email": "newuser@library.com",
  "password": "password123",
  "firstName": "New",
  "lastName": "User"
}
```

### **Available Roles Response:**
```json
[
  {
    "id": 4,
    "name": "MEMBER"
  }
]
```

---

## 📚 2. Book Management APIs

### **Read Operations** (All Roles: MEMBER, STAFF, LIBRARIAN, ADMINISTRATOR)

| Method | Endpoint | Description | Permission |
|--------|----------|-------------|------------|
| `GET` | `/api/books` | Get all books | All Roles |
| `GET` | `/api/books/available` | Get available books | All Roles |
| `GET` | `/api/books/{id}` | Get book by ID | All Roles |
| `GET` | `/api/books/isbn/{isbn}` | Get book by ISBN | All Roles |
| `GET` | `/api/books/language/{languageId}` | Get books by language | All Roles |
| `GET` | `/api/books/search?q={term}` | Search books | All Roles |

### **Write Operations** (Restricted Permissions)

| Method | Endpoint | Description | Permission |
|--------|----------|-------------|------------|
| `POST` | `/api/books` | Create new book | LIBRARIAN, ADMINISTRATOR |
| `PUT` | `/api/books/{id}` | Update book | LIBRARIAN, ADMINISTRATOR |
| `DELETE` | `/api/books/{id}` | Delete book | ADMINISTRATOR only |

### **Create Book Request Body:**
```json
{
  "title": "Harry Potter and the Philosopher's Stone",
  "isbn": "978-1234567890",
  "publicationYear": 1997,
  "edition": "1st Edition",
  "summary": "A magical story about a young wizard",
  "totalCopies": 5,
  "availableCopies": 5,
  "coverImageUrl": "https://example.com/cover.jpg",
  "categoryId": 1,
  "publisherId": 1,
  "languageId": 1,
  "authorIds": [1, 2]
}
```

---

## 👥 3. Member Management APIs

### **Read Operations** (STAFF, LIBRARIAN, ADMINISTRATOR)

| Method | Endpoint | Description | Permission |
|--------|----------|-------------|------------|
| `GET` | `/api/members` | Get all members | STAFF, LIBRARIAN, ADMINISTRATOR |
| `GET` | `/api/members/{id}` | Get member by ID | STAFF, LIBRARIAN, ADMINISTRATOR |
| `GET` | `/api/members/search?q={term}` | Search members | STAFF, LIBRARIAN, ADMINISTRATOR |

### **Write Operations** (LIBRARIAN, ADMINISTRATOR)

| Method | Endpoint | Description | Permission |
|--------|----------|-------------|------------|
| `POST` | `/api/members` | Create new member | LIBRARIAN, ADMINISTRATOR |
| `PUT` | `/api/members/{id}` | Update member | LIBRARIAN, ADMINISTRATOR |
| `DELETE` | `/api/members/{id}` | Delete member | ADMINISTRATOR only |

### **Create Member Request Body:**
```json
{
  "firstName": "Ahmed",
  "lastName": "Mohamed",
  "email": "ahmed@email.com",
  "phone": "01234567890",
  "address": "123 Main Street, Cairo",
  "membershipDate": "2024-01-15"
}
```

---

## 👤 4. System User Management APIs

### **User Management** (ADMINISTRATOR Only)

| Method | Endpoint | Description | Permission |
|--------|----------|-------------|------------|
| `GET` | `/api/users` | Get all users | ADMINISTRATOR |
| `GET` | `/api/users/{id}` | Get user by ID | ADMINISTRATOR |
| `GET` | `/api/users/username/{username}` | Get user by username | ADMINISTRATOR |
| `GET` | `/api/users/role/{roleId}` | Get users by role | ADMINISTRATOR |
| `POST` | `/api/users` | Create user | ADMINISTRATOR |
| `PUT` | `/api/users/{id}` | Update user | ADMINISTRATOR |
| `DELETE` | `/api/users/{id}` | Delete user | ADMINISTRATOR |

### **Role Management** (ADMINISTRATOR Only)

| Method | Endpoint | Description | Permission |
|--------|----------|-------------|------------|
| `GET` | `/api/roles` | Get all roles | ADMINISTRATOR |
| `GET` | `/api/roles/{id}` | Get role by ID | ADMINISTRATOR |
| `GET` | `/api/roles/name/{name}` | Get role by name | ADMINISTRATOR |
| `POST` | `/api/roles` | Create role | ADMINISTRATOR |
| `PUT` | `/api/roles/{id}` | Update role | ADMINISTRATOR |
| `DELETE` | `/api/roles/{id}` | Delete role | ADMINISTRATOR |

### **Create User Request Body:**
```json
{
  "username": "newstaff",
  "email": "staff@library.com",
  "password": "password123",
  "firstName": "Staff",
  "lastName": "User",
  "role": {
    "id": 3,
    "name": "STAFF"
  }
}
```

---

## 📖 5. Borrowing & Return Functionality APIs

### **Read Operations** (STAFF, LIBRARIAN, ADMINISTRATOR)

| Method | Endpoint | Description | Permission |
|--------|----------|-------------|------------|
| `GET` | `/api/borrowing` | Get all transactions | STAFF, LIBRARIAN, ADMINISTRATOR |
| `GET` | `/api/borrowing/{id}` | Get transaction by ID | STAFF, LIBRARIAN, ADMINISTRATOR |
| `GET` | `/api/borrowing/member/{memberId}` | Get member's transactions | STAFF, LIBRARIAN, ADMINISTRATOR |
| `GET` | `/api/borrowing/book/{bookId}` | Get book's transactions | STAFF, LIBRARIAN, ADMINISTRATOR |
| `GET` | `/api/borrowing/status/{status}` | Get transactions by status | STAFF, LIBRARIAN, ADMINISTRATOR |

### **Write Operations** (LIBRARIAN, ADMINISTRATOR)

| Method | Endpoint | Description | Permission |
|--------|----------|-------------|------------|
| `POST` | `/api/borrowing` | Borrow a book | LIBRARIAN, ADMINISTRATOR |
| `PUT` | `/api/borrowing/{id}` | Update transaction (Return/Lost) | LIBRARIAN, ADMINISTRATOR |
| `DELETE` | `/api/borrowing/{id}` | Delete transaction | ADMINISTRATOR only |

### **Transaction Statuses:**
- `BORROWED` - Book is currently borrowed
- `RETURNED` - Book has been returned
- `OVERDUE` - Book is past due date
- `LOST` - Book is reported lost

### **Create Transaction Request Body:**
```json
{
  "memberId": 1,
  "bookId": 1,
  "borrowDate": "2024-01-15",
  "dueDate": "2024-01-30",
  "status": "BORROWED"
}
```

---

## 📝 6. Author Management APIs

### **Read Operations** (All Roles: MEMBER, STAFF, LIBRARIAN, ADMINISTRATOR)

| Method | Endpoint | Description | Permission |
|--------|----------|-------------|------------|
| `GET` | `/api/authors` | Get all authors | All Roles |
| `GET` | `/api/authors/{id}` | Get author by ID | All Roles |
| `GET` | `/api/authors/search?q={term}` | Search authors | All Roles |

### **Write Operations** (LIBRARIAN, ADMINISTRATOR)

| Method | Endpoint | Description | Permission |
|--------|----------|-------------|------------|
| `POST` | `/api/authors` | Create author | LIBRARIAN, ADMINISTRATOR |
| `PUT` | `/api/authors/{id}` | Update author | LIBRARIAN, ADMINISTRATOR |
| `DELETE` | `/api/authors/{id}` | Delete author | ADMINISTRATOR only |

### **Create Author Request Body:**
```json
{
  "firstName": "J.K.",
  "lastName": "Rowling",
  "biography": "British author, best known for the Harry Potter series"
}
```

---

## 📊 7. User Activity Logging APIs

### **Security & Monitoring** (LIBRARIAN, ADMINISTRATOR Only)

| Method | Endpoint | Description | Permission |
|--------|----------|-------------|------------|
| `GET` | `/api/activity-logs` | Get all activity logs | LIBRARIAN, ADMINISTRATOR |
| `GET` | `/api/activity-logs/recent` | Get recent logs (last 50) | LIBRARIAN, ADMINISTRATOR |
| `GET` | `/api/activity-logs/user/{userId}` | Get logs by user | LIBRARIAN, ADMINISTRATOR |
| `GET` | `/api/activity-logs/search?q={term}` | Search activity logs | LIBRARIAN, ADMINISTRATOR |
| `GET` | `/api/activity-logs/count/user/{userId}` | Get user activity count | LIBRARIAN, ADMINISTRATOR |
| `GET` | `/api/activity-logs/count` | Get total activity count | LIBRARIAN, ADMINISTRATOR |

---

## 🏷️ 8. Supporting Entities APIs

### **Categories** (All Roles)

| Method | Endpoint | Description | Permission |
|--------|----------|-------------|------------|
| `GET` | `/api/categories` | Get all categories | All Roles |
| `GET` | `/api/categories/{id}` | Get category by ID | All Roles |
| `GET` | `/api/categories/search?q={term}` | Search categories | All Roles |
| `POST` | `/api/categories` | Create category | All Roles |
| `PUT` | `/api/categories/{id}` | Update category | All Roles |
| `DELETE` | `/api/categories/{id}` | Delete category | All Roles |

### **Publishers** (All Roles)

| Method | Endpoint | Description | Permission |
|--------|----------|-------------|------------|
| `GET` | `/api/publishers` | Get all publishers | All Roles |
| `GET` | `/api/publishers/{id}` | Get publisher by ID | All Roles |
| `GET` | `/api/publishers/search?q={term}` | Search publishers | All Roles |
| `POST` | `/api/publishers` | Create publisher | All Roles |
| `PUT` | `/api/publishers/{id}` | Update publisher | All Roles |
| `DELETE` | `/api/publishers/{id}` | Delete publisher | All Roles |

### **Languages** (All Roles)

| Method | Endpoint | Description | Permission |
|--------|----------|-------------|------------|
| `GET` | `/api/languages` | Get all languages | All Roles |
| `GET` | `/api/languages/{id}` | Get language by ID | All Roles |
| `GET` | `/api/languages/name/{name}` | Get language by name | All Roles |
| `POST` | `/api/languages` | Create language | All Roles |
| `PUT` | `/api/languages/{id}` | Update language | All Roles |
| `DELETE` | `/api/languages/{id}` | Delete language | All Roles |

---

## 🔐 Permission Summary

### **MEMBER Role:**
- ✅ Read: Books, Authors
- ❌ Write: Nothing
- ❌ Delete: Nothing

### **STAFF Role:**
- ✅ Read: Books, Authors, Members, Borrowing Transactions
- ❌ Write: Nothing
- ❌ Delete: Nothing

### **LIBRARIAN Role:**
- ✅ Read: Everything
- ✅ Write: Books, Authors, Members, Borrowing Transactions
- ❌ Delete: Nothing (except transactions)

### **ADMINISTRATOR Role:**
- ✅ Read: Everything
- ✅ Write: Everything
- ✅ Delete: Everything

---

## 📋 Sample Test Data

### **Default Users:**
```json
{
  "admin": {
    "username": "admin",
    "password": "admin123",
    "role": "ADMINISTRATOR"
  },
  "librarian": {
    "username": "librarian",
    "password": "librarian123",
    "role": "LIBRARIAN"
  },
  "staff": {
    "username": "staff",
    "password": "staff123",
    "role": "STAFF"
  },
  "member": {
    "username": "member",
    "password": "member123",
    "role": "MEMBER"
  }
}
```

### **Sample Books:**
- Harry Potter and the Philosopher's Stone
- The Great Gatsby
- 1984
- To Kill a Mockingbird
- Pride and Prejudice

### **Sample Members:**
- John Doe (john@email.com)
- Jane Smith (jane@email.com)
- Ahmed Mohamed (ahmed@email.com)

---

## 🚀 Getting Started

1. **Start the application:**
   ```bash
   mvn spring-boot:run
   ```

2. **Login with default admin:**
   ```bash
   POST http://localhost:8091/api/auth/login
   {
     "username": "admin",
     "password": "admin123"
   }
   ```

3. **Test the APIs using the provided Postman collection**

---

## 📝 Notes

- All endpoints return JSON responses
- Error responses include appropriate HTTP status codes
- Authentication is required for most endpoints (except public ones)
- Role-based access control is enforced on all endpoints
- User activity is automatically logged for audit purposes
- The system supports both Arabic and English content

---

**This documentation is updated and reflects the current state of the Library Management System after all recent modifications.**
