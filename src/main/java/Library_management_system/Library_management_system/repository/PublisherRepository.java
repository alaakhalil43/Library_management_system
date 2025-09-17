package Library_management_system.Library_management_system.repository;

import Library_management_system.Library_management_system.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
    
    List<Publisher> findByNameContainingIgnoreCase(String name);
    
    @Query("SELECT p FROM Publisher p WHERE p.name LIKE %:name% ORDER BY p.name")
    List<Publisher> findByNameContainingOrderByName(@Param("name") String name);
}
