# 📥 Postman Collection Import Guide

## 🚀 Quick Start

### Step 1: Download the Collection
1. Go to the repository: `Library_management_system.postman_collection.json`
2. Click the **"Raw"** button (top right)
3. Copy all the content (Ctrl+A, Ctrl+C)

### Step 2: Import into Postman
1. Open **Postman** application
2. Click **"Import"** button (top left corner)
3. Select **"Raw text"** tab
4. Paste the copied JSON content
5. Click **"Continue"** then **"Import"**

### Step 3: Set Up Environment
1. Click **"Environments"** in the left sidebar
2. Click **"Create Environment"**
3. Name it: `Library Management System`
4. Add these variables:
   - `baseUrl`: `http://localhost:8095`
   - `token`: (leave empty for now)

### Step 4: Start Testing
1. Select the environment you just created
2. Go to **"Collections"** → **"Library Management System"**
3. Find **"🔐 Authentication & User Management"** folder
4. Run **"User Login"** request
5. Copy the `token` from the response
6. Set the `token` variable in your environment

## 🔑 Default Credentials

| Role | Username | Password |
|------|----------|----------|
| **Administrator** | `admin` | `admin123` |
| **Librarian** | `librarian1` | `admin123` |
| **Staff** | `staff1` | `admin123` |
| **Member** | Register new | Any password |

## 📚 Collection Structure

### 🔐 Authentication & User Management
- User Login/Logout
- User Registration
- User Management (Admin only)
- Role Management

### 📚 Book Management
- Get All Books
- Search Books
- Create/Update/Delete Books
- Book by ISBN/Language

### 👥 Member Management
- Member CRUD operations
- Member Search
- Membership Management

### 📖 Borrowing & Return System
- Borrow Books
- Return Books
- Transaction History
- Status Management

### 📝 Author Management
- Author CRUD operations
- Author Search
- Biography Management

### 🏷️ Supporting Entities
- Categories Management
- Publishers Management
- Languages Management

## 🎯 Testing Scenarios

### 1. **Admin Testing**
- Login as `admin`
- Test all CRUD operations
- Manage users and roles
- Access all endpoints

### 2. **Librarian Testing**
- Login as `librarian1`
- Test book management
- Test borrowing operations
- Test member management

### 3. **Staff Testing**
- Login as `staff1`
- Test limited operations
- Test member management
- Test borrowing operations

### 4. **Member Testing**
- Register new member
- Test book browsing
- Test limited access

## 🔧 Troubleshooting

### Common Issues:

1. **"Unauthorized" Error**
   - Make sure you're logged in
   - Check if token is set in environment
   - Verify token is not expired

2. **"Forbidden" Error**
   - Check user role permissions
   - Some endpoints require specific roles

3. **"Connection Refused" Error**
   - Make sure the application is running
   - Check if port 8095 is correct
   - Verify database connection

4. **"Token Invalid" Error**
   - Re-login to get new token
   - Check if token variable is set correctly

## 📊 API Endpoints Summary

| Endpoint | Method | Role Required | Description |
|----------|--------|---------------|-------------|
| `/api/auth/login` | POST | None | User login |
| `/api/auth/logout` | POST | Authenticated | User logout |
| `/api/books` | GET | MEMBER+ | Get all books |
| `/api/books` | POST | LIBRARIAN+ | Create book |
| `/api/members` | GET | STAFF+ | Get all members |
| `/api/members` | POST | STAFF+ | Create member |
| `/api/borrowing` | GET | MEMBER+ | Get transactions |
| `/api/borrowing` | POST | LIBRARIAN+ | Borrow book |
| `/api/users` | GET | ADMIN | Get all users |
| `/api/users` | POST | ADMIN | Create user |

## 🎉 Success!

Once imported, you'll have access to **50+ pre-configured API requests** ready for testing!

---

**Need Help?** Check the main README.md for more details about the application setup.