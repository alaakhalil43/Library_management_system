package Library_management_system.Library_management_system.repository;

import Library_management_system.Library_management_system.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    
    Optional<Book> findByIsbn(String isbn);
    
    boolean existsByIsbn(String isbn);
    
    List<Book> findByTitleContainingIgnoreCase(String title);
    
    @Query("SELECT DISTINCT b FROM Book b JOIN b.authors a WHERE a.firstName LIKE %:author% OR a.lastName LIKE %:author%")
    List<Book> findByAuthorContainingIgnoreCase(@Param("author") String author);
    
    List<Book> findByAvailableCopiesGreaterThan(int copies);
    
    @Query("SELECT DISTINCT b FROM Book b LEFT JOIN b.authors a WHERE b.title LIKE %:searchTerm% OR b.isbn LIKE %:searchTerm% OR a.firstName LIKE %:searchTerm% OR a.lastName LIKE %:searchTerm%")
    List<Book> findByTitleOrIsbnOrAuthorContaining(@Param("searchTerm") String searchTerm);
    
    @Query("SELECT b FROM Book b WHERE b.publicationYear = :year")
    List<Book> findByPublicationYear(@Param("year") Integer year);
    
    @Query("SELECT b FROM Book b WHERE b.publicationYear BETWEEN :startYear AND :endYear")
    List<Book> findByPublicationYearBetween(@Param("startYear") Integer startYear, @Param("endYear") Integer endYear);
    
    List<Book> findByLanguageId(Integer languageId);
    
    List<Book> findByEditionContainingIgnoreCase(String edition);
    
    @Query("SELECT b FROM Book b WHERE b.summary LIKE %:searchTerm%")
    List<Book> findBySummaryContaining(@Param("searchTerm") String searchTerm);
}
