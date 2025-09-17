package Library_management_system.Library_management_system.repository;

import Library_management_system.Library_management_system.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    
    Optional<Role> findByName(String name);
    
    boolean existsByName(String name);
    
    List<Role> findByNameContainingIgnoreCase(String name);
    
    @Query("SELECT r FROM Role r WHERE r.name LIKE %:name% ORDER BY r.name")
    List<Role> findByNameContainingOrderByName(@Param("name") String name);
}
