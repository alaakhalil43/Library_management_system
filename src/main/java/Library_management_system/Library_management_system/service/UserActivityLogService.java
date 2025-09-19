package Library_management_system.Library_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Library_management_system.Library_management_system.model.User;
import Library_management_system.Library_management_system.model.UserActivityLog;
import Library_management_system.Library_management_system.repository.UserActivityLogRepository;

@Service
@Transactional
public class UserActivityLogService {
    
    @Autowired
    private UserActivityLogRepository userActivityLogRepository;
    
    // Log activity
    public UserActivityLog logActivity(User user, String action) {
        UserActivityLog log = new UserActivityLog(user, action);
        return userActivityLogRepository.save(log);
    }
    
    // Get all activity logs
    public List<UserActivityLog> getAllActivityLogs() {
        return userActivityLogRepository.findAll();
    }
    
    // Get recent activity logs (last 50)
    public List<UserActivityLog> getRecentActivityLogs() {
        return userActivityLogRepository.findRecentLogs();
    }
    
    // Get activity logs by user
    public List<UserActivityLog> getActivityLogsByUser(User user) {
        return userActivityLogRepository.findByUserOrderByTimestampDesc(user);
    }
    
    // Get activity logs by user ID
    public List<UserActivityLog> getActivityLogsByUserId(Integer userId) {
        return userActivityLogRepository.findByUserIdOrderByTimestampDesc(userId);
    }

    // Count activities by user
    public Long countActivitiesByUser(User user) {
        return userActivityLogRepository.countByUser(user);
    }
    
    // Count activities by user ID
    public Long countActivitiesByUserId(Integer userId) {
        return userActivityLogRepository.countByUserId(userId);
    }
    
    // Get total activity logs count
    public Long getTotalActivityLogsCount() {
        return userActivityLogRepository.count();
    }
}

