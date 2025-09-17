package Library_management_system.Library_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Library_management_system.Library_management_system.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    // Simple repository with basic CRUD operations
}