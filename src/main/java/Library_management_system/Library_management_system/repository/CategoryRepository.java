package Library_management_system.Library_management_system.repository;

import Library_management_system.Library_management_system.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    
    List<Category> findByNameContainingIgnoreCase(String name);
    
    @Query("SELECT c FROM Category c WHERE c.name LIKE %:name% ORDER BY c.name")
    List<Category> findByNameContainingOrderByName(@Param("name") String name);
}
