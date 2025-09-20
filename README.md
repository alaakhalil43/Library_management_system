# 📚 Library Management System

## 🎯 Complete Spring Boot Application

A production-ready Library Management System built with Java, Spring Boot, and MySQL. This comprehensive system provides complete CRUD operations, role-based access control, JWT authentication, and user activity logging.

## ✨ Key Features

### 🔐 Authentication & Security
- **JWT-based stateless authentication**
- **Password encryption using BCrypt**
- **Token blacklisting for secure logout**
- **Role-based endpoint protection**
- **4 User Roles**: ADMINISTRATOR, LIBRARIAN, STAFF, MEMBER

### 📊 Database Management
- **11 Main Tables** with proper relationships
- **Foreign Key Constraints** for data integrity
- **Many-to-Many Relationships** (Books ↔ Authors)
- **Self-Referencing Relationships** (Categories hierarchy)
- **Junction Tables** for complex relationships
- **Audit Trail** with user activity logs

### 🚀 API Features
- **50+ RESTful endpoints**
- **Complete CRUD operations** for all entities
- **Role-based permissions** for each endpoint
- **Comprehensive error handling**
- **Input validation** with proper error messages
- **Search and filtering** capabilities

### 📱 User Management
- **System Users**: Staff with different roles
- **Library Members**: Public users who borrow books
- **Role Management**: Dynamic role assignment
- **User Activity Logging**: Complete audit trail

## 🛠️ Technology Stack

- **Backend**: Java 17, Spring Boot 3.x
- **Security**: Spring Security, JWT
- **Database**: MySQL 8.0
- **ORM**: JPA/Hibernate
- **Build Tool**: Maven
- **Migration**: Flyway
- **Testing**: Postman Collection

## 📊 Screenshots

### Database Entity Relationship Diagram (ERD)

![Database ERD](POSTMAN_GUIDE.md)

*Complete Entity Relationship Diagram showing all database tables and their relationships*

### API Testing with Postman

![Postman Collection](assets/postman-screenshot.png)

*Complete API testing collection with 50+ endpoints*

## 🗄️ Database Schema

### Core Entities
- **Users**: System users with roles (ADMINISTRATOR, LIBRARIAN, STAFF, MEMBER)
- **Members**: Library members (public users)
- **Books**: Book catalog with extended metadata
- **Authors**: Book authors with biographies
- **Categories**: Book categories (hierarchical structure)
- **Publishers**: Book publishers with contact information
- **Languages**: Book languages (multi-language support)
- **BorrowingTransactions**: Complete borrowing records
- **UserActivityLogs**: User activity audit trail


## 🚀 Quick Start

### Prerequisites
- Java 17 or higher
- MySQL 8.0 or higher
- Maven 3.6 or higher

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/alaakhalil43/Library_management_system.git
   cd Library_management_system
   ```

2. **Configure Database**
   - Create MySQL database: `library_management`
   - Update `application.properties` with your database credentials

3. **Run the Application**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the API**
   - Base URL: `http://localhost:8095`
   - API Documentation: Available in Postman Collection

## 🧪 Testing

### Postman Collection
- **Complete API testing collection included**
- **50+ RESTful endpoints** with role-based permissions
- **JWT Authentication** with token management
- **Pre-configured requests** for all entities

### 📥 How to Import Postman Collection:

> **📖 Detailed Guide**: [POSTMAN_GUIDE.md](POSTMAN_GUIDE.md) - Complete step-by-step instructions

1. **Download the Collection:**
   - Click on `Library_management_system.postman_collection.json` in the repository
   - Click the **"Raw"** button to view the raw JSON
   - Copy all the content (Ctrl+A, Ctrl+C)

2. **Import into Postman:**
   - Open Postman application
   - Click **"Import"** button (top left)
   - Select **"Raw text"** tab
   - Paste the copied JSON content
   - Click **"Continue"** then **"Import"**

3. **Set Environment Variables:**
   - Create a new environment in Postman
   - Add variable: `baseUrl` = `http://localhost:8095`
   - Add variable: `token` = (leave empty, will be set after login)

4. **Start Testing:**
   - Use **"User Login"** request to get authentication token
   - Copy the token from response and set it in the `token` variable
   - All other requests will automatically use the token

### 🔑 Default Credentials:
- **Admin**: `admin` / `admin123`
- **Librarian**: `librarian1` / `admin123`
- **Staff**: `staff1` / `admin123`

## 📚 API Endpoints

### 🔐 Authentication & User Management
- `POST /api/auth/login` - User login
- `POST /api/auth/logout` - User logout
- `GET /api/auth/me` - Get current user
- `GET /api/auth/status` - Check authentication status
- `POST /api/users/register` - Public user registration
- `GET /api/users` - Get all users (ADMIN only)
- `PUT /api/users/{id}/role` - Update user role (ADMIN only)

### 📚 Book Management
- `GET /api/books` - Get all books (MEMBER+)
- `GET /api/books/{id}` - Get book by ID
- `GET /api/books/isbn/{isbn}` - Get book by ISBN
- `GET /api/books/language/{languageId}` - Get books by language
- `GET /api/books/search?q={query}` - Search books
- `POST /api/books` - Create book (LIBRARIAN+)
- `PUT /api/books/{id}` - Update book (LIBRARIAN+)
- `DELETE /api/books/{id}` - Delete book (ADMIN only)

### 👥 Member Management
- `GET /api/members` - Get all members (STAFF+)
- `GET /api/members/{id}` - Get member by ID
- `GET /api/members/search?q={query}` - Search members
- `POST /api/members` - Create member (STAFF+)
- `PUT /api/members/{id}` - Update member (STAFF+)
- `DELETE /api/members/{id}` - Delete member (STAFF+)

### 📖 Borrowing & Return System
- `GET /api/borrowing` - Get all transactions (MEMBER+)
- `GET /api/borrowing/{id}` - Get transaction by ID
- `GET /api/borrowing/member/{memberId}` - Get member transactions
- `GET /api/borrowing/book/{bookId}` - Get book transactions (STAFF+)
- `GET /api/borrowing/status/{status}` - Get transactions by status (STAFF+)
- `POST /api/borrowing` - Borrow book (LIBRARIAN+)
- `PUT /api/borrowing/{id}` - Return book (LIBRARIAN+)
- `DELETE /api/borrowing/{id}` - Delete transaction (ADMIN only)

### 📝 Author Management
- `GET /api/authors` - Get all authors (MEMBER+)
- `GET /api/authors/{id}` - Get author by ID
- `GET /api/authors/search?q={query}` - Search authors
- `POST /api/authors` - Create author (LIBRARIAN+)
- `PUT /api/authors/{id}` - Update author (LIBRARIAN+)
- `DELETE /api/authors/{id}` - Delete author (ADMIN only)

### 🏷️ Supporting Entities
- **Categories**: `GET/POST/PUT/DELETE /api/categories`
- **Publishers**: `GET/POST/PUT/DELETE /api/publishers`
- **Languages**: `GET/POST/PUT/DELETE /api/languages`

## 🔐 Role-Based Permissions

### ADMINISTRATOR
- ✅ All CRUD operations on all entities
- ✅ User management and role assignment
- ✅ System configuration
- ✅ Complete access to all endpoints

### LIBRARIAN
- ✅ Book management (create, update, delete)
- ✅ Member management
- ✅ Borrowing operations
- ✅ Author management
- ❌ User management (except own profile)

### STAFF
- ✅ Member management
- ✅ Borrowing operations
- ✅ Author management
- ✅ Book viewing
- ❌ Book creation/update
- ❌ User management

### MEMBER
- ✅ Book browsing and searching
- ✅ View own borrowing history
- ✅ Public registration
- ❌ All management operations

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

## 🎯 What Makes This Special

### 🏗️ Architecture
- **Production-ready code** with proper error handling
- **Scalable architecture** for future enhancements
- **Security-first approach** with JWT and role-based access
- **Modern Java practices** and Spring Boot best practices

### 🔒 Security Features
- **JWT Authentication** with token blacklisting
- **Password encryption** using BCrypt
- **Role-based access control** for all endpoints
- **Input validation** and sanitization
- **SQL injection protection** through JPA

### 📈 Performance
- **Database indexing** for optimal query performance
- **Connection pooling** for database efficiency
- **Stateless authentication** for scalability
- **Optimized queries** with proper relationships

### 🧪 Testing & Documentation
- **Complete Postman collection** with 50+ endpoints
- **Comprehensive API documentation**
- **Sample data** for immediate testing
- **Role-based test scenarios**

## 🚀 Future Enhancements

The system is designed to support future enhancements:

- **Multi-branch library support**
- **Advanced reporting features**
- **Integration with external systems**
- **Mobile application support**
- **Advanced analytics and reporting**
- **Email notifications**
- **Fine management system**

## 📁 Project Structure

```
Library_management_system/
├── src/main/java/
│   └── Library_management_system/
│       ├── config/          # Configuration classes
│       ├── controller/      # REST controllers
│       ├── model/          # Entity models
│       ├── repository/     # Data access layer
│       ├── service/        # Business logic
│       └── security/       # Security configuration
├── src/main/resources/
│   ├── db/migration/       # Database migrations
│   └── application.properties
├── docs/                   # Documentation
├── assets/                 # Images and screenshots
├── Library_management_system.postman_collection.json
├── POSTMAN_GUIDE.md
└── README.md
```

## 🤝 Contributing

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request
