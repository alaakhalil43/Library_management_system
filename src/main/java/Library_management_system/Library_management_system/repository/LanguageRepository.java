package Library_management_system.Library_management_system.repository;

import Library_management_system.Library_management_system.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {
    
    Optional<Language> findByName(String name);
    
    boolean existsByName(String name);
    
    List<Language> findByNameContainingIgnoreCase(String name);
    
    @Query("SELECT l FROM Language l WHERE l.name LIKE %:name% ORDER BY l.name")
    List<Language> findByNameContainingOrderByName(@Param("name") String name);
}
