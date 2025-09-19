# دليل نظام تسجيل أنشطة المستخدمين البسيط

## نظرة عامة

تم إضافة نظام بسيط لتسجيل أنشطة المستخدمين إلى نظام إدارة المكتبة. هذا النظام يتتبع جميع العمليات التي يقوم بها المستخدمون في النظام.

## المكونات المضافة

### 1. نموذج البيانات (Data Model)
- **UserActivityLog**: جدول بسيط لتسجيل الأنشطة
- **الحقول**: id, user_id, action, timestamp

### 2. الخدمات (Services)
- **UserActivityLogService**: خدمة بسيطة لإدارة سجلات الأنشطة
- **UserActivityLogRepository**: طبقة الوصول للبيانات

### 3. API Endpoints
- **UserActivityLogController**: 6 endpoints بسيطة لعرض سجلات الأنشطة
- **الصلاحيات**: المديرين والمكتبيين فقط

## كيفية العمل

### التسجيل التلقائي
النظام يسجل الأنشطة تلقائياً في الخدمات:

```java
// في BookService
public Book saveBook(Book book) {
    Book savedBook = bookRepository.save(book);
    
    // تسجيل النشاط تلقائياً
    userActivityLogService.logActivity(null, "Added new book: " + book.getTitle());
    
    return savedBook;
}
```

### الأنشطة المسجلة
- ✅ **إضافة الكتب**: "Added new book: [عنوان الكتاب]"
- ✅ **تحديث الكتب**: "Updated book: [عنوان الكتاب]"
- ✅ **حذف الكتب**: "Deleted book: [عنوان الكتاب]"
- ✅ **إضافة الأعضاء**: "Added new member: [اسم العضو]"
- ✅ **تحديث الأعضاء**: "Updated member: [اسم العضو]"
- ✅ **حذف الأعضاء**: "Deleted member: [اسم العضو]"
- ✅ **استعارة الكتب**: "Book borrowed: [عنوان الكتاب] by [اسم العضو]"
- ✅ **تحديث المعاملات**: "Updated borrowing transaction for book: [عنوان الكتاب]"
- ✅ **حذف المعاملات**: "Deleted borrowing transaction for book: [عنوان الكتاب]"

## API Endpoints

### عرض سجلات الأنشطة

| Method | Endpoint | الوصف |
|--------|----------|--------|
| GET | `/api/activity-logs` | جميع السجلات |
| GET | `/api/activity-logs/recent` | السجلات الأخيرة (آخر 50) |
| GET | `/api/activity-logs/user/{userId}` | سجلات مستخدم معين |
| GET | `/api/activity-logs/search?q={term}` | البحث في النصوص |
| GET | `/api/activity-logs/count/user/{userId}` | عدد سجلات مستخدم |
| GET | `/api/activity-logs/count` | العدد الإجمالي |

## أمثلة على الاستخدام

### 1. الحصول على جميع الأنشطة

```bash
curl -X GET "http://localhost:8080/api/activity-logs" \
  -H "Authorization: Bearer YOUR_TOKEN"
```

### 2. البحث في الأنشطة

```bash
curl -X GET "http://localhost:8080/api/activity-logs/search?q=book" \
  -H "Authorization: Bearer YOUR_TOKEN"
```

### 3. أنشطة مستخدم معين

```bash
curl -X GET "http://localhost:8080/api/activity-logs/user/1" \
  -H "Authorization: Bearer YOUR_TOKEN"
```

### 4. عدد الأنشطة

```bash
curl -X GET "http://localhost:8080/api/activity-logs/count" \
  -H "Authorization: Bearer YOUR_TOKEN"
```

## قاعدة البيانات

### جدول user_activity_logs
```sql
CREATE TABLE user_activity_logs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    action VARCHAR(255) NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
```

### بيانات تجريبية
تم إضافة 9 سجلات تجريبية في migration script V3.

## الأمان

- **المديرون (ADMINISTRATOR)**: وصول كامل لجميع endpoints
- **المكتبيون (LIBRARIAN)**: وصول لجميع endpoints
- **الموظفون (STAFF)**: لا يمكنهم الوصول لسجلات الأنشطة

## التكوين

### 1. Database Migration
تم إنشاء migration script جديد `V3__Create_user_activity_logs_table.sql`

### 2. Security Configuration
تم إضافة endpoints جديدة إلى `SecurityConfig`:

```java
.requestMatchers("/api/activity-logs/**").hasAnyRole("ADMINISTRATOR", "LIBRARIAN")
```

## الملفات المضافة

1. **UserActivityLog.java** - نموذج البيانات
2. **UserActivityLogRepository.java** - طبقة البيانات
3. **UserActivityLogService.java** - طبقة الخدمات
4. **UserActivityLogController.java** - طبقة التحكم
5. **V3__Create_user_activity_logs_table.sql** - Migration script
6. **User_Activity_Logs_Postman_Collection.json** - Postman collection

## الملفات المحدثة

1. **BookService.java** - إضافة تسجيل الأنشطة
2. **MemberService.java** - إضافة تسجيل الأنشطة
3. **BorrowingTransactionService.java** - إضافة تسجيل الأنشطة
4. **SecurityConfig.java** - إضافة صلاحيات جديدة
5. **README.md** - إضافة وثائق النظام

## الاختبار

### 1. تشغيل التطبيق
```bash
mvn spring-boot:run
```

### 2. اختبار API
استخدم Postman collection المرفق أو curl commands

### 3. التحقق من قاعدة البيانات
```sql
SELECT * FROM user_activity_logs ORDER BY timestamp DESC;
```

## مثال على الاستجابة

```json
[
  {
    "id": 1,
    "user": null,
    "action": "Added new book: Harry Potter and the Philosopher's Stone",
    "timestamp": "2024-01-15T10:30:00"
  },
  {
    "id": 2,
    "user": null,
    "action": "Updated member: Alice Johnson",
    "timestamp": "2024-01-15T10:25:00"
  }
]
```

## الخلاصة

نظام تسجيل أنشطة المستخدمين البسيط يوفر:

- ✅ **تسجيل تلقائي** لجميع العمليات
- ✅ **API بسيط** لعرض السجلات
- ✅ **أمان مناسب** مع الصلاحيات
- ✅ **سهولة الاستخدام** والتنفيذ
- ✅ **وثائق شاملة** وواضحة

هذا النظام يجعل نظام إدارة المكتبة أكثر أماناً وشفافية! 🎉


