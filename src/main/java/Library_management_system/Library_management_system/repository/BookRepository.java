package Library_management_system.Library_management_system.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Library_management_system.Library_management_system.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    
    // Find book by ISBN
    Optional<Book> findByIsbn(String isbn);
    
    // Check if book exists by ISBN
    boolean existsByIsbn(String isbn);
    
    // Find books by title (case insensitive)
    List<Book> findByTitleContainingIgnoreCase(String title);
}