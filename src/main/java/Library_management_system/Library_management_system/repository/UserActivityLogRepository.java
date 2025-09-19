package Library_management_system.Library_management_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Library_management_system.Library_management_system.model.User;
import Library_management_system.Library_management_system.model.UserActivityLog;

@Repository
public interface UserActivityLogRepository extends JpaRepository<UserActivityLog, Integer> {
    
    // Find logs by user
    List<UserActivityLog> findByUserOrderByTimestampDesc(User user);
    
    // Find logs by user ID
    List<UserActivityLog> findByUserIdOrderByTimestampDesc(Integer userId);
    
    // Find recent logs (last 50)
    @Query("SELECT ual FROM UserActivityLog ual ORDER BY ual.timestamp DESC")
    List<UserActivityLog> findRecentLogs();

    
    // Count logs by user
    Long countByUser(User user);
    
    // Count logs by user ID
    Long countByUserId(Integer userId);
}

