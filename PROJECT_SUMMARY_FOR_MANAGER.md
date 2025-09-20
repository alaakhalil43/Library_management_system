# Library Management System - Project Summary

## 🎯 **Project Overview**
A complete Library Management System built with Java Spring Boot that manages books, members, staff, and borrowing transactions with role-based access control.

## 🏗️ **System Architecture**

### **4 Main Components:**
1. **Models** - Data structures (User, Book, Member, etc.)
2. **Repositories** - Database access layer
3. **Services** - Business logic layer
4. **Controllers** - API endpoints layer

### **Database Tables:**
- `users` - System users (admin, librarian, staff, members)
- `books` - Library books with metadata
- `members` - Library members (borrowers)
- `authors` - Book authors
- `borrowing_transactions` - Book borrowing records
- `roles` - User roles and permissions

## 👥 **User Roles & Permissions**

| Role | Description | What They Can Do |
|------|-------------|------------------|
| **MEMBER** | Library members | • View books and authors<br>• Search catalog |
| **STAFF** | Library staff | • View all data (read-only)<br>• Cannot create/update/delete |
| **LIBRARIAN** | Senior staff | • Manage books and authors<br>• Manage members and borrowing<br>• View activity logs |
| **ADMINISTRATOR** | System admin | • Full system access<br>• Manage all users and roles<br>• Delete anything |

## 🔧 **Key Features**

### **1. Book Management**
- Add, update, delete books
- Search books by title, author, or content
- Track available copies
- Book metadata (ISBN, edition, summary, etc.)

### **2. Member Management**
- Register new library members
- Update member information
- Search members by name or email

### **3. Borrowing System**
- Borrow books to members
- Return books
- Track borrowing history
- Monitor overdue books

### **4. User Management**
- Role-based access control
- Secure authentication
- Activity logging
- Public registration (defaults to MEMBER)

### **5. Security**
- Password encryption (BCrypt)
- Role-based permissions
- Secure API endpoints
- User activity tracking

## 📊 **API Endpoints Summary**

### **Authentication**
- `POST /api/auth/login` - User login
- `POST /api/auth/logout` - User logout
- `GET /api/auth/me` - Get current user
- `POST /api/users/register` - Public registration

### **Books**
- `GET /api/books` - List all books
- `GET /api/books/{id}` - Get book by ID
- `GET /api/books/isbn/{isbn}` - Get book by ISBN
- `GET /api/books/language/{languageId}` - Get books by language
- `GET /api/books/search?q=term` - Search books
- `POST /api/books` - Add book (Librarian+)
- `PUT /api/books/{id}` - Update book (Librarian+)
- `DELETE /api/books/{id}` - Delete book (Admin only)

### **Members**
- `GET /api/members` - List all members
- `GET /api/members/{id}` - Get member by ID
- `GET /api/members/search?q=term` - Search members
- `POST /api/members` - Add member (Librarian+)
- `PUT /api/members/{id}` - Update member (Librarian+)
- `DELETE /api/members/{id}` - Delete member (Admin only)

### **Borrowing**
- `GET /api/borrowing` - List all transactions
- `GET /api/borrowing/member/{id}` - Get member's transactions
- `POST /api/borrowing` - Borrow book (Librarian+)
- `PUT /api/borrowing/{id}` - Return book (Librarian+)
- `DELETE /api/borrowing/{id}` - Delete transaction (Admin only)

## 🛠️ **Technology Stack**

- **Backend**: Java 21, Spring Boot 3.x
- **Database**: MySQL 8.0
- **Security**: Spring Security with BCrypt
- **ORM**: Spring Data JPA
- **Migration**: Flyway
- **Build Tool**: Maven

## 🚀 **How to Run**

1. **Prerequisites**: Java 21, MySQL 8.0, Maven
2. **Database**: Create database named `library_management`
3. **Configuration**: Update `application.properties` with database credentials
4. **Run**: `mvn spring-boot:run`
5. **Access**: API available at `http://localhost:8091`

## 📈 **Business Value**

### **For Library Staff:**
- Efficient book management
- Easy member registration
- Quick borrowing/return process
- Activity tracking and reporting

### **For Library Members:**
- Easy book search and discovery
- Self-service registration
- View borrowing history

### **For Management:**
- Complete system oversight
- User activity monitoring
- Role-based access control
- Data security and integrity

## 🔒 **Security Features**

- **Authentication**: Username/password login
- **Authorization**: Role-based access control
- **Password Security**: BCrypt encryption
- **Activity Logging**: Track all user actions
- **API Security**: Protected endpoints based on roles

## 📝 **Sample Data**

The system comes with sample data:
- 4 user accounts (admin, librarian, staff, member)
- 8 sample books with authors
- 5 sample library members
- Sample borrowing transactions
- Activity logs

## 🎯 **Key Benefits**

1. **Simple & Clean**: Easy to understand and maintain
2. **Secure**: Role-based access control
3. **Scalable**: Can handle growing library needs
4. **User-Friendly**: Intuitive API design
5. **Audit Trail**: Complete activity logging
6. **Flexible**: Easy to add new features

## 📞 **Support**

The system is well-documented with:
- Complete API documentation
- Postman collection for testing
- Clear code structure
- Database schema documentation

---

**This system provides a complete, secure, and user-friendly solution for library management with proper role-based access control and comprehensive functionality.**
