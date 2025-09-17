package Library_management_system.Library_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Library_management_system.Library_management_system.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    // Simple repository with basic CRUD operations
}