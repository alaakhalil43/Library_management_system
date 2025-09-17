package Library_management_system.Library_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Library_management_system.Library_management_system.model.Book;
import Library_management_system.Library_management_system.repository.BookRepository;

@Service
public class BookService {
    
    @Autowired
    private BookRepository bookRepository;
    
    // Get all books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    
    // Get book by ID
    public Book getBookById(Integer id) {
        return bookRepository.findById(id).orElse(null);
    }
    
    // Get book by ISBN
    public Book getBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn).orElse(null);
    }
    
    // Search books
    public List<Book> searchBooks(String searchTerm) {
        return bookRepository.findByTitleContainingIgnoreCase(searchTerm);
    }
    
    // Save book
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }
    
    // Update book
    public Book updateBook(Integer id, Book bookDetails) {
        Book book = getBookById(id);
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
        
        return bookRepository.save(book);
    }
    
    // Delete book
    public boolean deleteBook(Integer id) {
        Book book = getBookById(id);
        if (book == null) {
            return false;
        }
        
        bookRepository.delete(book);
        return true;
    }
}