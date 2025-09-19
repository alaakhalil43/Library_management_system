package Library_management_system.Library_management_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import Library_management_system.Library_management_system.model.Book;
import Library_management_system.Library_management_system.model.User;
import Library_management_system.Library_management_system.repository.BookRepository;

@Service
public class BookService {
    
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private UserActivityLogService userActivityLogService;
    
    // Method to get current user from Security Context
    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            return (User) authentication.getPrincipal();
        }
        return null;
    }
    
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    
    public Book getBookById(Integer id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return book.get();
        }
        return null;
    }
    
    public Book getBookByIsbn(String isbn) {
        Optional<Book> book = bookRepository.findByIsbn(isbn);
        if (book.isPresent()) {
            return book.get();
        }
        return null;
    }
    
    public List<Book> searchBooks(String searchTerm) {
        return bookRepository.findByTitleOrIsbnOrAuthorContaining(searchTerm);
    }
    
    public List<Book> getAvailableBooks() {
        return bookRepository.findByAvailableCopiesGreaterThan(0);
    }
    
    public List<Book> getBooksByLanguageId(Integer languageId) {
        return bookRepository.findByLanguageId(languageId);
    }
    
    public List<Book> getBooksByEdition(String edition) {
        return bookRepository.findByEditionContainingIgnoreCase(edition);
    }
    
    public List<Book> searchBooksBySummary(String searchTerm) {
        return bookRepository.findBySummaryContaining(searchTerm);
    }
    
    public Book saveBook(Book book) {
        if (bookRepository.existsByIsbn(book.getIsbn())) {
            return null; // Book already exists
        }
        Book savedBook = bookRepository.save(book);
        
        // Log activity with current user
        User currentUser = getCurrentUser();
        userActivityLogService.logActivity(currentUser, "Added new book: " + book.getTitle());
        
        return savedBook;
    }
    
    public Book updateBook(Integer id, Book bookDetails) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null) {
            return null;
        }
        
        book.setTitle(bookDetails.getTitle());
        book.setIsbn(bookDetails.getIsbn());
        book.setAuthors(bookDetails.getAuthors());
        book.setPublicationYear(bookDetails.getPublicationYear());
        book.setLanguage(bookDetails.getLanguage());
        book.setEdition(bookDetails.getEdition());
        book.setSummary(bookDetails.getSummary());
        book.setCoverImageUrl(bookDetails.getCoverImageUrl());
        book.setTotalCopies(bookDetails.getTotalCopies());
        book.setAvailableCopies(bookDetails.getAvailableCopies());
        book.setCategory(bookDetails.getCategory());
        book.setPublisher(bookDetails.getPublisher());
        
        Book updatedBook = bookRepository.save(book);
        
        // Log activity with current user
        User currentUser = getCurrentUser();
        userActivityLogService.logActivity(currentUser, "Updated book: " + book.getTitle());
        
        return updatedBook;
    }
    
    public boolean deleteBook(Integer id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null) {
            return false;
        }
        
        String bookTitle = book.getTitle();
        bookRepository.delete(book);
        
        // Log activity with current user
        User currentUser = getCurrentUser();
        userActivityLogService.logActivity(currentUser, "Deleted book: " + bookTitle);
        
        return true;
    }
    
    public void updateAvailableCopies(Integer bookId, int change) {
        Book book = bookRepository.findById(bookId).orElse(null);
        if (book != null) {
            int newAvailableCopies = book.getAvailableCopies() + change;
            book.setAvailableCopies(newAvailableCopies);
            bookRepository.save(book);
        }
    }
}
