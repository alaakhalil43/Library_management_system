package Library_management_system.Library_management_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Library_management_system.Library_management_system.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    
    // Find authors by first name
    List<Author> findByFirstNameContainingIgnoreCase(String firstName);
    
    // Find authors by last name
    List<Author> findByLastNameContainingIgnoreCase(String lastName);
    
    // Find authors by full name
    @Query("SELECT a FROM Author a WHERE CONCAT(a.firstName, ' ', a.lastName) LIKE %:name%")
    List<Author> findByFullNameContaining(@Param("name") String name);
    
    // Search authors by biography
    List<Author> findByBiographyContainingIgnoreCase(String biography);
    
    // Find authors by first and last name
    List<Author> findByFirstNameAndLastName(String firstName, String lastName);
}