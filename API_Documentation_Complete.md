# 📚 Library Management System - Complete API Documentation

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

## 🔐 1. Authentication APIs

### **User Authentication & Session Management**

| Method | Endpoint | Description | Permission |
|--------|----------|-------------|------------|
| `POST` | `/api/auth/login` | User login | Public |
| `POST` | `/api/auth/logout` | User logout | Authenticated |
| `GET` | `/api/auth/me` | Get current user info | Authenticated |
| `GET` | `/api/auth/status` | Check auth status | Public |
| `POST` | `/api/users/register` | Public user registration (defaults to MEMBER) | Public |

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

---

## 📚 2. Book Management APIs

### **Read Operations** (All Roles: MEMBER, STAFF, LIBRARIAN, ADMINISTRATOR)

| Method | Endpoint | Description | Permission |
|--------|----------|-------------|------------|
| `GET` | `/api/books` | Get all books | All Roles |
| `GET` | `/api/books/available` | Get available books | All Roles |
| `GET` | `/api/books/{id}` | Get book by ID | All Roles |
| `GET` | `/api/books/isbn/{isbn}` | Get book by ISBN | All Roles |
| `GET` | `/api/books/search?q={term}` | Search books | All Roles |
| `GET` | `/api/books/language/{languageId}` | Get books by language | All Roles |
| `GET` | `/api/books/edition?edition={edition}` | Get books by edition | All Roles |
| `GET` | `/api/books/search-summary?q={term}` | Search by summary | All Roles |

### **Write Operations** (Restricted Permissions)

| Method | Endpoint | Description | Permission |
|--------|----------|-------------|------------|
| `POST` | `/api/books` | Create new book | ADMINISTRATOR, LIBRARIAN |
| `PUT` | `/api/books/{id}` | Update book | ADMINISTRATOR, LIBRARIAN |
| `DELETE` | `/api/books/{id}` | Delete book | ADMINISTRATOR only |

---

## 👥 3. Member Management APIs

### **Complete CRUD Operations** (Mixed Permissions)

| Method | Endpoint | Description | Permission |
|--------|----------|-------------|------------|
| `GET` | `/api/members` | Get all members | STAFF, LIBRARIAN, ADMINISTRATOR |
| `GET` | `/api/members/{id}` | Get member by ID | STAFF, LIBRARIAN, ADMINISTRATOR |
| `GET` | `/api/members/search?q={term}` | Search members | STAFF, LIBRARIAN, ADMINISTRATOR |
| `POST` | `/api/members` | Create new member | LIBRARIAN, ADMINISTRATOR |
| `PUT` | `/api/members/{id}` | Update member | LIBRARIAN, ADMINISTRATOR |
| `DELETE` | `/api/members/{id}` | Delete member | ADMINISTRATOR only |

---

## 👤 4. System User Management APIs

### **User Management with Permission Levels**

| Method | Endpoint | Description | Permission |
|--------|----------|-------------|------------|
| `GET` | `/api/users` | Get all users | ADMINISTRATOR only |
| `GET` | `/api/users/{id}` | Get user by ID | ADMINISTRATOR only |
| `GET` | `/api/users/username/{username}` | Get user by username | ADMINISTRATOR only |
| `GET` | `/api/users/role/{roleId}` | Get users by role | ADMINISTRATOR only |
| `POST` | `/api/users` | Create user | ADMINISTRATOR only |
| `POST` | `/api/users/register` | Public registration | Public (No Auth) |
| `GET` | `/api/users/available-roles` | Get available roles | Public (No Auth) |
| `PUT` | `/api/users/{id}` | Update user | ADMINISTRATOR only |
| `DELETE` | `/api/users/{id}` | Delete user | ADMINISTRATOR only |

### **Role Management** (Admin Only)

| Method | Endpoint | Description | Permission |
|--------|----------|-------------|------------|
| `GET` | `/api/roles` | Get all roles | ADMINISTRATOR |
| `GET` | `/api/roles/{id}` | Get role by ID | ADMINISTRATOR |
| `GET` | `/api/roles/name/{name}` | Get role by name | ADMINISTRATOR |
| `POST` | `/api/roles` | Create role | ADMINISTRATOR |
| `PUT` | `/api/roles/{id}` | Update role | ADMINISTRATOR |
| `DELETE` | `/api/roles/{id}` | Delete role | ADMINISTRATOR |

---

## 📖 5. Borrowing & Return Functionality APIs

### **Transaction Management** (Mixed Permissions)

| Method | Endpoint | Description | Permission |
|--------|----------|-------------|------------|
| `GET` | `/api/borrowing` | Get all transactions | STAFF, LIBRARIAN, ADMINISTRATOR |
| `GET` | `/api/borrowing/{id}` | Get transaction by ID | STAFF, LIBRARIAN, ADMINISTRATOR |
| `GET` | `/api/borrowing/member/{memberId}` | Get member's transactions | STAFF, LIBRARIAN, ADMINISTRATOR |
| `GET` | `/api/borrowing/book/{bookId}` | Get book's transactions | STAFF, LIBRARIAN, ADMINISTRATOR |
| `GET` | `/api/borrowing/status/{status}` | Get transactions by status | STAFF, LIBRARIAN, ADMINISTRATOR |
| `POST` | `/api/borrowing` | Borrow a book | LIBRARIAN, ADMINISTRATOR |
| `PUT` | `/api/borrowing/{id}` | Update transaction (Return/Lost) | LIBRARIAN, ADMINISTRATOR |
| `DELETE` | `/api/borrowing/{id}` | Delete transaction | ADMINISTRATOR only |

### **Transaction Statuses:**
- `BORROWED` - Book is currently borrowed
- `RETURNED` - Book has been returned
- `OVERDUE` - Book is past due date
- `LOST` - Book is reported lost

---

## 📝 6. Author Management APIs

### **Extended Book Metadata** (Mixed Permissions)

| Method | Endpoint | Description | Permission |
|--------|----------|-------------|------------|
| `GET` | `/api/authors` | Get all authors | MEMBER, STAFF, LIBRARIAN, ADMINISTRATOR |
| `GET` | `/api/authors/{id}` | Get author by ID | MEMBER, STAFF, LIBRARIAN, ADMINISTRATOR |
| `GET` | `/api/authors/search?q={term}` | Search authors | MEMBER, STAFF, LIBRARIAN, ADMINISTRATOR |
| `GET` | `/api/authors/first-name?firstName={name}` | Get by first name | MEMBER, STAFF, LIBRARIAN, ADMINISTRATOR |
| `GET` | `/api/authors/last-name?lastName={name}` | Get by last name | MEMBER, STAFF, LIBRARIAN, ADMINISTRATOR |
| `GET` | `/api/authors/full-name?name={name}` | Get by full name | MEMBER, STAFF, LIBRARIAN, ADMINISTRATOR |
| `GET` | `/api/authors/biography?biography={term}` | Search by biography | MEMBER, STAFF, LIBRARIAN, ADMINISTRATOR |
| `POST` | `/api/authors` | Create author | LIBRARIAN, ADMINISTRATOR |
| `PUT` | `/api/authors/{id}` | Update author | LIBRARIAN, ADMINISTRATOR |
| `DELETE` | `/api/authors/{id}` | Delete author | ADMINISTRATOR only |

---

## 📊 7. User Activity Logging APIs

### **Security & Monitoring** (Admin/Librarian Only)

| Method | Endpoint | Description | Permission |
|--------|----------|-------------|------------|
| `GET` | `/api/activity-logs` | Get all activity logs | ADMINISTRATOR, LIBRARIAN |
| `GET` | `/api/activity-logs/recent` | Get recent logs (last 50) | ADMINISTRATOR, LIBRARIAN |
| `GET` | `/api/activity-logs/user/{userId}` | Get logs by user | ADMINISTRATOR, LIBRARIAN |
| `GET` | `/api/activity-logs/count/user/{userId}` | Get user activity count | ADMINISTRATOR, LIBRARIAN |
| `GET` | `/api/activity-logs/count` | Get total activity count | ADMINISTRATOR, LIBRARIAN |

---

## 🏷️ 8. Supporting Entities APIs

### **Categories**

| Method | Endpoint | Description | Permission |
|--------|----------|-------------|------------|
| `GET` | `/api/categories` | Get all categories | All Roles |
| `GET` | `/api/categories/{id}` | Get category by ID | All Roles |
| `GET` | `/api/categories/search?q={term}` | Search categories | All Roles |
| `POST` | `/api/categories` | Create category | All Roles |
| `PUT` | `/api/categories/{id}` | Update category | All Roles |
| `DELETE` | `/api/categories/{id}` | Delete category | All Roles |

### **Publishers**

| Method | Endpoint | Description | Permission |
|--------|----------|-------------|------------|
| `GET` | `/api/publishers` | Get all publishers | All Roles |
| `GET` | `/api/publishers/{id}` | Get publisher by ID | All Roles |
| `GET` | `/api/publishers/search?q={term}` | Search publishers | All Roles |
| `POST` | `/api/publishers` | Create publisher | All Roles |
| `PUT` | `/api/publishers/{id}` | Update publisher | All Roles |
| `DELETE` | `/api/publishers/{id}` | Delete publisher | All Roles |

### **Languages**

| Method | Endpoint | Description | Permission |
|--------|----------|-------------|------------|
| `GET` | `/api/languages` | Get all languages | All Roles |
| `GET` | `/api/languages/{id}` | Get language by ID | All Roles |
| `GET` | `/api/languages/name/{name}` | Get language by name | All Roles |
| `POST` | `/api/languages` | Create language | All Roles |
| `PUT` | `/api/languages/{id}` | Update language | All Roles |
| `DELETE` | `/api/languages/{id}` | Delete language | All Roles |

---

## 🔐 Security & Authentication

### **Role-Based Access Control (RBAC)**

| Role | Permissions |
|------|-------------|
| **ADMINISTRATOR** | Full access to all operations |
| **LIBRARIAN** | Book management, Member management, User management, Activity logs |
| **STAFF** | Read access to books, members, borrowing transactions |

### **Authentication**
- **Type**: Spring Security with BCrypt password encoding
- **Method**: Username/Password authentication
- **Session**: Stateless (JWT-ready)

---

## 👥 User Roles & Permissions

### **Role Hierarchy & Capabilities:**

| Role | Description | Permissions |
|------|-------------|-------------|
| **MEMBER** | Library members (borrowers) | • View books and authors<br>• Search and browse catalog<br>• Cannot manage other users or books |
| **STAFF** | Library staff | • View books, authors, members, and borrowing transactions<br>• Read-only access to all data<br>• Cannot create, update, or delete anything |
| **LIBRARIAN** | Senior library staff | • All STAFF permissions<br>• Create/update books and authors<br>• Manage members and borrowing transactions<br>• View activity logs<br>• Cannot manage system users or roles |
| **ADMINISTRATOR** | System administrator | • All permissions<br>• Manage all users and roles<br>• Delete books, authors, and members<br>• Full system access<br>• Activity monitoring |

### **Default Registration:**
- Public registration defaults to **MEMBER** role
- Only ADMINISTRATOR can create other roles
- ADMINISTRATOR account is pre-created in database

---

## 📋 Postman Collection

### **Collection Structure:**
1. **🔐 Authentication** - Login, logout, and session management
2. **📚 Book Management** - All book-related operations
3. **👥 Member Management** - Member CRUD operations
4. **👤 System User Management** - User management with roles
5. **📖 Borrowing & Return System** - Transaction management
6. **📝 Author Management** - Author CRUD operations
7. **📊 Activity Logs** - User activity monitoring
8. **🏷️ Supporting Entities** - Categories, Publishers, Languages, Roles

### **Environment Variables:**
- `baseUrl`: `http://localhost:8091`

---

## 🎯 Task Completion Status

### ✅ **Required Features:**
- [x] **Book Management** - Complete CRUD with role-based permissions
- [x] **Member Management** - Complete CRUD operations
- [x] **System User Management** - Different permission levels implemented
- [x] **Borrowing and Return Functionality** - Complete transaction management
- [x] **RESTful API Design** - All endpoints follow REST conventions
- [x] **Role-Based Access Control** - 3 roles with hierarchical permissions

### ✅ **Bonus Features:**
- [x] **Postman Collection** - Complete organized collection provided
- [x] **User Activity Logging** - Security and monitoring system
- [x] **Author Management** - Extended book metadata support
- [x] **Supporting Entities** - Categories, Publishers, Languages, Roles
- [x] **Search Functionality** - Advanced search across all entities
- [x] **Comprehensive Documentation** - Complete API documentation

---

## 🚀 Getting Started

1. **Import Postman Collection**: Use `Library_Management_System_Complete_API_Collection.json`
2. **Set Environment**: Configure `baseUrl` to `http://localhost:8091`
3. **Start Application**: Run the Spring Boot application
4. **Test APIs**: Use the organized Postman collection to test all endpoints

---

**🎉 The Library Management System API is complete and ready for use!**
