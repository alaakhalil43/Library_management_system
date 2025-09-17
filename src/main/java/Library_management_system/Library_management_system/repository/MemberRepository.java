package Library_management_system.Library_management_system.repository;

import Library_management_system.Library_management_system.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    
    Optional<Member> findByMemberId(String memberId);
    
    Optional<Member> findByEmail(String email);
    
    boolean existsByMemberId(String memberId);
    
    boolean existsByEmail(String email);
    
    @Query("SELECT m FROM Member m WHERE m.firstName LIKE %:name% OR m.lastName LIKE %:name%")
    List<Member> findByNameContaining(@Param("name") String name);
    
    @Query("SELECT m FROM Member m WHERE m.memberId LIKE %:memberId%")
    List<Member> findByMemberIdContaining(@Param("memberId") String memberId);
}
