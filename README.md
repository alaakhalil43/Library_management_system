# Library Management System

A simple Spring Boot application for managing library operations including books, members, and borrowing transactions.

## Features

- **Book Management**: Add, update, delete, and search books
- **Member Management**: Register and manage library members
- **Borrowing System**: Track book borrowing and returns
- **User Management**: Manage system users with different roles
- **RESTful API**: Simple REST endpoints for all operations

## Technology Stack

- Java 21
- Spring Boot 3.5.5
- Spring Data JPA
- Spring Security
- MySQL Database
- Flyway for database migrations
- Maven for dependency management

## Database Schema

The system uses a simple database schema with 6 main tables:

1. **users** - System users (admin, librarian, staff)
2. **books** - Library books with basic information
3. **members** - Library members
4. **categories** - Book categories/genres
5. **publishers** - Book publishers
6. **borrowing_transactions** - Book borrowing records

## API Endpoints

### Books
- `GET /api/books` - Get all books
- `GET /api/books/{id}` - Get book by ID
- `GET /api/books/available` - Get available books
- `GET /api/books/search?q={term}` - Search books
- `POST /api/books` - Create new book
- `PUT /api/books/{id}` - Update book
- `DELETE /api/books/{id}` - Delete book

### Members
- `GET /api/members` - Get all members
- `GET /api/members/{id}` - Get member by ID
- `GET /api/members/search?q={term}` - Search members
- `POST /api/members` - Create new member
- `PUT /api/members/{id}` - Update member
- `DELETE /api/members/{id}` - Delete member

### Borrowing Transactions
- `GET /api/borrowing` - Get all transactions
- `GET /api/borrowing/{id}` - Get transaction by ID
- `GET /api/borrowing/member/{memberId}` - Get member's transactions
- `POST /api/borrowing/borrow` - Borrow a book
- `PUT /api/borrowing/{id}/return` - Return a book
- `PUT /api/borrowing/{id}/lost` - Mark book as lost

### Users
- `GET /api/users` - Get all users
- `GET /api/users/{id}` - Get user by ID
- `POST /api/users` - Create new user
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user

### Categories
- `GET /api/categories` - Get all categories
- `GET /api/categories/{id}` - Get category by ID
- `GET /api/categories/search?q={term}` - Search categories
- `POST /api/categories` - Create new category
- `PUT /api/categories/{id}` - Update category
- `DELETE /api/categories/{id}` - Delete category

### Publishers
- `GET /api/publishers` - Get all publishers
- `GET /api/publishers/{id}` - Get publisher by ID
- `GET /api/publishers/search?q={term}` - Search publishers
- `POST /api/publishers` - Create new publisher
- `PUT /api/publishers/{id}` - Update publisher
- `DELETE /api/publishers/{id}` - Delete publisher

## Setup Instructions

1. **Prerequisites**
   - Java 21 or higher
   - MySQL 8.0 or higher
   - Maven 3.6 or higher

2. **Database Setup**
   - Create a MySQL database named `library_management`
   - Update `application.properties` with your database credentials

3. **Run the Application**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the Application**
   - API Base URL: `http://localhost:8080`
   - H2 Console: `http://localhost:8080/h2-console` (if using H2)

## Default Users

The system comes with sample users:
- **Admin**: username: `admin`, password: `admin123`
- **Librarian**: username: `librarian1`, password: `admin123`
- **Staff**: username: `staff1`, password: `admin123`

## Sample Data

The application includes sample data:
- 8 sample books with different authors and publishers
- 5 sample members
- 6 sample categories
- 5 sample publishers
- Sample borrowing transactions

## Project Structure

```
src/main/java/
├── Library_management_system/
│   └── Library_management_system/
│       ├── controller/     # REST controllers
│       ├── model/          # JPA entities
│       ├── repository/     # Data access layer
│       ├── service/        # Business logic
│       └── security/       # Security configuration
src/main/resources/
├── db/migration/           # Flyway migration scripts
└── application.properties  # Application configuration
```

## Design Decisions

1. **Simple Database Schema**: Used basic tables with foreign key relationships
2. **Basic Validation**: Simple validation using JPA annotations
3. **RESTful Design**: Standard REST endpoints for CRUD operations
4. **Role-based Security**: Three user roles (ADMIN, LIBRARIAN, STAFF)
5. **MySQL with Flyway**: Database migrations for version control
6. **Simple Error Handling**: Basic null checks and simple error responses

## Testing

You can test the API using:
- Postman collection (included)
- curl commands
- Any REST client

Example curl command:
```bash
# Get all books
curl -X GET http://localhost:8080/api/books

# Create a new book
curl -X POST http://localhost:8080/api/books \
  -H "Content-Type: application/json" \
  -d '{"title":"Test Book","isbn":"1234567890","author":"Test Author","publicationYear":2024,"totalCopies":1,"availableCopies":1}'
```

## Future Improvements

- Add more validation
- Implement proper error handling
- Add logging
- Add unit tests
- Implement pagination
- Add more search filters
