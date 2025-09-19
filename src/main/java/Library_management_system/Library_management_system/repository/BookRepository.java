package Library_management_system.Library_management_system.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Library_management_system.Library_management_system.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    
    Optional<Book> findByIsbn(String isbn);
    
    boolean existsByIsbn(String isbn);
    
    List<Book> findByAvailableCopiesGreaterThan(int copies);
    
    @Query("SELECT DISTINCT b FROM Book b LEFT JOIN b.authors a WHERE b.title LIKE %:searchTerm% OR b.isbn LIKE %:searchTerm% OR a.firstName LIKE %:searchTerm% OR a.lastName LIKE %:searchTerm%")
    List<Book> findByTitleOrIsbnOrAuthorContaining(@Param("searchTerm") String searchTerm);
    
    List<Book> findByLanguageId(Integer languageId);
    
    List<Book> findByEditionContainingIgnoreCase(String edition);
    
    @Query("SELECT b FROM Book b WHERE b.summary LIKE %:searchTerm%")
    List<Book> findBySummaryContaining(@Param("searchTerm") String searchTerm);
}
