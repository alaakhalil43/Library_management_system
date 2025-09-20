# Database ERD Image

## 📊 Entity Relationship Diagram

This directory contains the visual representation of the database schema.

### 📁 Files:
- `database_erd.png` - Complete Entity Relationship Diagram

### 🎯 Image Content:
The ERD shows all database tables and their relationships:

#### **Core Tables:**
- **roles** - System user roles (ADMINISTRATOR, LIBRARIAN, STAFF, MEMBER)
- **users** - System users with authentication
- **members** - Library members (public users)
- **books** - Book catalog with metadata
- **authors** - Book authors
- **book_authors** - Junction table for many-to-many relationship

#### **Supporting Tables:**
- **categories** - Book categories (hierarchical)
- **publishers** - Book publishers
- **languages** - Book languages
- **borrowing_transactions** - Borrowing records
- **user_activity_logs** - Activity audit trail

### 🔗 Key Relationships:
- **Users** → **Roles** (Many-to-One)
- **Books** ↔ **Authors** (Many-to-Many via book_authors)
- **Books** → **Categories** (Many-to-One)
- **Books** → **Publishers** (Many-to-One)
- **Books** → **Languages** (Many-to-One)
- **Borrowing Transactions** → **Books, Members, Users** (Many-to-One each)
- **Categories** → **Categories** (Self-referencing for hierarchy)

### 📝 Usage:
This image is referenced in:
- `README.md` - Main project documentation
- `docs/ERD.md` - Detailed database documentation

### 🎨 Image Requirements:
- **Format**: PNG (recommended) or JPG
- **Resolution**: High quality for clear viewing
- **Content**: All tables with relationships clearly visible
- **Labels**: Table and field names clearly readable