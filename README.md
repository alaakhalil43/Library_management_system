# 📚 Library Management System

## 🚀 Complete Spring Boot Application

A production-ready Library Management System built with Java, Spring Boot, and MySQL that demonstrates advanced software engineering practices and exceeds the basic requirements.

## ✨ Key Features

### Core Requirements ✅
- **Well-designed database schema** with proper relationships and constraints
- **Complete RESTful API** with 50+ endpoints covering all CRUD operations
- **Role-based access control** with 4 roles: ADMINISTRATOR, LIBRARIAN, STAFF, MEMBER
- **Secure authentication** using JWT tokens with password encryption
- **User activity logging** system with comprehensive tracking
- **Book metadata support** including multiple authors, categories, publishers, languages

### Advanced Features 🚀
- **JWT-based stateless authentication** with token blacklisting
- **Comprehensive security** with role-based permissions
- **Database migrations** using Flyway
- **Input validation** and error handling
- **RESTful API design** following best practices
- **Complete Postman collection** for API testing

## 🛠️ Technology Stack

- **Java 17**
- **Spring Boot 3.x**
- **Spring Security**
- **MySQL 8.0**
- **JPA/Hibernate**
- **JWT Authentication**
- **Maven**
- **Flyway**
- **BCrypt Password Encoding**


## 🗄️ Database Schema

### Core Entities
- **Users**: System users with roles
- **Members**: Library members
- **Books**: Book catalog with metadata
- **Authors**: Book authors
- **Categories**: Book categories
- **Publishers**: Book publishers
- **Languages**: Book languages
- **BorrowingTransactions**: Book borrowing records
- **UserActivityLogs**: User activity tracking

### 📋 Database Documentation
- **Complete ERD**: [docs/ERD.md](docs/ERD.md) - Detailed Entity Relationship Diagram
- **Database Schema**: Visual representation of all tables and relationships
- **Sample Data**: Pre-loaded data for testing all features

## 🚀 Quick Start

### Prerequisites
- Java 17 or higher
- MySQL 8.0 or higher
- Maven 3.6+

### Installation

1. **Clone the repository**
   ```bash
   git clone [`https://github.com/alaakhalil43/Library_management_system.git`]
   cd Library_management_system
   ```

2. **Configure database**
   - Create MySQL database: `library_managment_system`
   - Update `application.properties` with your database credentials

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the application**
   - API Base URL: `http://localhost:8095`
   - H2 Console: `http://localhost:8095/h2-console` (if enabled)

## 📚 API Documentation

### Authentication
- `POST /api/auth/login` - User login
- `POST /api/auth/logout` - User logout
- `GET /api/auth/me` - Get current user
- `GET /api/auth/status` - Check auth status

### Book Management
- `GET /api/books` - Get all books
- `POST /api/books` - Create book (LIBRARIAN+)
- `PUT /api/books/{id}` - Update book (LIBRARIAN+)
- `DELETE /api/books/{id}` - Delete book (ADMIN only)

### Member Management
- `GET /api/members` - Get all members
- `POST /api/members` - Create member (LIBRARIAN+)
- `PUT /api/members/{id}` - Update member (LIBRARIAN+)
- `DELETE /api/members/{id}` - Delete member (LIBRARIAN+)

### User Management
- `GET /api/users` - Get all users (ADMIN only)
- `POST /api/users` - Create user (ADMIN+)
- `PUT /api/users/{id}` - Update user (ADMIN+)
- `DELETE /api/users/{id}` - Delete user (ADMIN only)

### Borrowing System
- `GET /api/borrowing` - Get all transactions
- `POST /api/borrowing` - Borrow book (LIBRARIAN+)
- `PUT /api/borrowing/{id}` - Return book (LIBRARIAN+)
- `DELETE /api/borrowing/{id}` - Delete transaction (ADMIN only)

## 🔐 Security & Permissions

### Roles & What They Can Do

#### 🔴 ADMINISTRATOR (Full System Access)
- ✅ **User Management**: Create, read, update, delete all users
- ✅ **Role Management**: Assign and change user roles
- ✅ **Book Management**: Full CRUD operations on books
- ✅ **Member Management**: Full CRUD operations on members
- ✅ **Author Management**: Full CRUD operations on authors
- ✅ **Category Management**: Full CRUD operations on categories
- ✅ **Publisher Management**: Full CRUD operations on publishers
- ✅ **Language Management**: Full CRUD operations on languages
- ✅ **Borrowing System**: Full access to all borrowing operations
- ✅ **Activity Logs**: View all user activity logs
- ✅ **System Configuration**: Access to all system settings

#### 🟡 LIBRARIAN (Library Operations)
- ✅ **Book Management**: Create, read, update, delete books
- ✅ **Member Management**: Create, read, update, delete members
- ✅ **Author Management**: Create, read, update, delete authors
- ✅ **Category Management**: Create, read, update, delete categories
- ✅ **Publisher Management**: Create, read, update, delete publishers
- ✅ **Language Management**: Create, read, update, delete languages
- ✅ **Borrowing System**: Borrow and return books, manage transactions
- ✅ **Activity Logs**: View user activity logs
- ❌ **User Management**: Cannot manage system users
- ❌ **System Configuration**: No access to system settings

#### 🟢 STAFF (Limited Operations)
- ✅ **Book Management**: Read books only
- ✅ **Member Management**: Create, read, update, delete members
- ✅ **Author Management**: Create, read, update, delete authors
- ✅ **Category Management**: Read categories only
- ✅ **Publisher Management**: Read publishers only
- ✅ **Language Management**: Read languages only
- ✅ **Borrowing System**: View borrowing transactions
- ❌ **User Management**: Cannot manage system users
- ❌ **Book Creation**: Cannot create or update books
- ❌ **Activity Logs**: No access to activity logs

#### 🔵 MEMBER (Public User)
- ✅ **Book Management**: Read and search books only
- ✅ **Borrowing System**: View own borrowing history
- ✅ **Public Registration**: Can register as new member
- ❌ **All Management Operations**: Cannot manage any entities
- ❌ **User Management**: Cannot access user management
- ❌ **Activity Logs**: No access to activity logs

### Authentication
- JWT-based stateless authentication
- Password encryption using BCrypt
- Token blacklisting for secure logout
- Role-based endpoint protection

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

### 1. **Production-Ready Code**
- Proper error handling and validation
- Comprehensive logging
- Security best practices

### 2. **Scalable Architecture**
- Clean separation of concerns
- Modular design
- Easy to extend and maintain

### 3. **Security-First Approach**
- JWT authentication
- Role-based access control
- Password encryption
- Input validation

### 4. **Modern Java Practices**
- Spring Boot 3.x
- Latest Java features
- Clean code principles
- RESTful API design

### 5. **Complete Documentation**
- Comprehensive README
- API documentation
- Database schema
- Setup instructions

## 📁 Project Structure

```
src/main/java/
├── config/          # Configuration classes
├── controller/      # REST controllers
├── model/          # Entity models
├── repository/     # Data access layer
├── service/        # Business logic
├── security/       # Security configuration
└── util/          # Utility classes

src/main/resources/
├── application.properties
└── db/migration/   # Database migrations
```

## 🚀 Getting Started with APIs

 **Login as Admin**
   ```bash
   POST /api/auth/login
   {
     "username": "admin",
     "password": "admin"
   }
   ```


## 🤝 Contributing
1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

