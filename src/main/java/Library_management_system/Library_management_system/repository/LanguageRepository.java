package Library_management_system.Library_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Library_management_system.Library_management_system.model.Language;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {
    // Simple repository with basic CRUD operations
}