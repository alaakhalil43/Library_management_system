package Library_management_system.Library_management_system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Library_management_system.Library_management_system.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    
    // Find member by email
    Optional<Member> findByEmail(String email);
    
    // Check if member exists by email
    boolean existsByEmail(String email);
}