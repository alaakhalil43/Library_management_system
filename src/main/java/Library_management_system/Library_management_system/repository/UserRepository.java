package Library_management_system.Library_management_system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Library_management_system.Library_management_system.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
    // Find user by username
    Optional<User> findByUsername(String username);
    
    // Check if user exists by username
    boolean existsByUsername(String username);
    
    // Check if user exists by email
    boolean existsByEmail(String email);
}