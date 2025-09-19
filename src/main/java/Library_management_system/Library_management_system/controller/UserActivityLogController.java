package Library_management_system.Library_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Library_management_system.Library_management_system.model.UserActivityLog;
import Library_management_system.Library_management_system.service.UserActivityLogService;

@RestController
@RequestMapping("/api/activity-logs")
@CrossOrigin(origins = "*")
public class UserActivityLogController {
    
    @Autowired
    private UserActivityLogService userActivityLogService;
    
    // Get all activity logs (Admin/Librarian only)
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'LIBRARIAN')")
    public List<UserActivityLog> getAllActivityLogs() {
        return userActivityLogService.getAllActivityLogs();
    }
    
    // Get recent activity logs (last 50)
    @GetMapping("/recent")
    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'LIBRARIAN')")
    public List<UserActivityLog> getRecentActivityLogs() {
        return userActivityLogService.getRecentActivityLogs();
    }
    
    // Get activity logs by user ID
    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'LIBRARIAN')")
    public List<UserActivityLog> getActivityLogsByUserId(@PathVariable Integer userId) {
        return userActivityLogService.getActivityLogsByUserId(userId);
    }

    // Get activity count by user ID
    @GetMapping("/count/user/{userId}")
    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'LIBRARIAN')")
    public Long getActivityCountByUserId(@PathVariable Integer userId) {
        return userActivityLogService.countActivitiesByUserId(userId);
    }
    
    // Get total activity logs count
    @GetMapping("/count")
    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'LIBRARIAN')")
    public Long getTotalActivityLogsCount() {
        return userActivityLogService.getTotalActivityLogsCount();
    }
}

