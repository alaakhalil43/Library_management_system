package Library_management_system.Library_management_system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Library_management_system.Library_management_system.model.Role;
import Library_management_system.Library_management_system.model.User;
import Library_management_system.Library_management_system.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    // إدارة المستخدمين - ADMINISTRATOR فقط
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }
    
    @GetMapping("/username/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }
    
    @GetMapping("/role/{roleId}")
    public List<User> getUsersByRoleId(@PathVariable Integer roleId) {
        return userService.getUsersByRoleId(roleId);
    }
    
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.saveUser(user);
        if (createdUser != null) {
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    // Register endpoint - لا يحتاج authentication
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        // Generate username if not provided
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            String generatedUsername = user.getFirstName().toLowerCase() + "_" + System.currentTimeMillis();
            user.setUsername(generatedUsername);
        }
        // تحديد Role افتراضي (MEMBER) إذا لم يتم تحديده
        if (user.getRole() == null) {
            Role defaultRole = new Role();
            defaultRole.setId(4); // MEMBER role (id=4)
            defaultRole.setName("MEMBER");
            user.setRole(defaultRole);
        }
        
        User createdUser = userService.saveUser(user);
        if (createdUser != null) {
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    // Get available roles for registration - لا يحتاج authentication
    @GetMapping("/available-roles")
    public ResponseEntity<List<Role>> getAvailableRoles() {
        List<Role> roles = userService.getAvailableRolesForRegistration();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(id, userDetails);
        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // تحديث role للمستخدم - ADMINISTRATOR فقط
    @PutMapping("/{id}/role")
    public ResponseEntity<Map<String, Object>> updateUserRole(@PathVariable Integer id, @RequestBody Map<String, Object> request) {
        try {
            Integer roleId = (Integer) request.get("roleId");
            String roleName = (String) request.get("roleName");
            
            if (roleId == null && roleName == null) {
                Map<String, Object> response = new HashMap<>();
                response.put("success", false);
                response.put("message", "Either roleId or roleName must be provided");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            
            User updatedUser = userService.updateUserRole(id, roleId, roleName);
            
            if (updatedUser != null) {
                Map<String, Object> response = new HashMap<>();
                response.put("success", true);
                response.put("message", "User role updated successfully");
                response.put("user", updatedUser);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                Map<String, Object> response = new HashMap<>();
                response.put("success", false);
                response.put("message", "User not found or role not found");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error updating user role: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
