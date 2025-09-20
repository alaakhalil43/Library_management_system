package Library_management_system.Library_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Library_management_system.Library_management_system.model.Book;
import Library_management_system.Library_management_system.service.BookService;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*")
public class BookController {
    
    @Autowired
    private BookService bookService;
    
    // جميع المستخدمين يمكنهم القراءة (بما في ذلك MEMBER)
    @GetMapping
    @PreAuthorize("hasAnyRole('MEMBER', 'STAFF', 'LIBRARIAN', 'ADMINISTRATOR')")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }
    
    @GetMapping("/available")
    @PreAuthorize("hasAnyRole('MEMBER', 'STAFF', 'LIBRARIAN', 'ADMINISTRATOR')")
    public List<Book> getAvailableBooks() {
        return bookService.getAvailableBooks();
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('MEMBER', 'STAFF', 'LIBRARIAN', 'ADMINISTRATOR')")
    public Book getBookById(@PathVariable Integer id) {
        return bookService.getBookById(id);
    }
    
    @GetMapping("/isbn/{isbn}")
    @PreAuthorize("hasAnyRole('MEMBER', 'STAFF', 'LIBRARIAN', 'ADMINISTRATOR')")
    public Book getBookByIsbn(@PathVariable String isbn) {
        return bookService.getBookByIsbn(isbn);
    }
    
    @GetMapping("/language/{languageId}")
    @PreAuthorize("hasAnyRole('MEMBER', 'STAFF', 'LIBRARIAN', 'ADMINISTRATOR')")
    public List<Book> getBooksByLanguageId(@PathVariable Integer languageId) {
        return bookService.getBooksByLanguageId(languageId);
    }
    
    // البحث - جميع المستخدمين (بما في ذلك MEMBER)
    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('MEMBER', 'STAFF', 'LIBRARIAN', 'ADMINISTRATOR')")
    public List<Book> searchBooks(@RequestParam String q) {
        return bookService.searchBooks(q);
    }
    
    // إضافة كتاب - LIBRARIAN+ فقط
    @PostMapping
    @PreAuthorize("hasAnyRole('LIBRARIAN', 'ADMINISTRATOR')")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book createdBook = bookService.saveBook(book);
        if (createdBook != null) {
            return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    // تحديث كتاب - LIBRARIAN+ فقط
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('LIBRARIAN', 'ADMINISTRATOR')")
    public ResponseEntity<Book> updateBook(@PathVariable Integer id, @RequestBody Book bookDetails) {
        Book updatedBook = bookService.updateBook(id, bookDetails);
        if (updatedBook != null) {
            return new ResponseEntity<>(updatedBook, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // حذف كتاب - ADMINISTRATOR فقط
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer id) {
        boolean deleted = bookService.deleteBook(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
