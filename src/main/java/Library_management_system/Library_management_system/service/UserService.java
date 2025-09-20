package Library_management_system.Library_management_system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Library_management_system.Library_management_system.model.Role;
import Library_management_system.Library_management_system.model.User;
import Library_management_system.Library_management_system.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }
    
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
    
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
    
    public List<User> searchUsers(String searchTerm) {
        return userRepository.findByNameContaining(searchTerm);
    }
    
    public List<User> getUsersByRoleId(Integer roleId) {
        return userRepository.findByRoleId(roleId);
    }
    
    public User saveUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return null; // Username already exists
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            return null; // Email already exists
        }
        
        // Encode password before saving
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        
        return userRepository.save(user);
    }
    
    public User updateUser(Integer id, User userDetails) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return null;
        }
        
        // Check if username is being changed and if new username already exists
        if (!user.getUsername().equals(userDetails.getUsername()) && 
            userRepository.existsByUsername(userDetails.getUsername())) {
            return null; // Username already exists
        }
        
        // Check if email is being changed and if new email already exists
        if (!user.getEmail().equals(userDetails.getEmail()) && 
            userRepository.existsByEmail(userDetails.getEmail())) {
            return null; // Email already exists
        }
        
        // Update user fields
        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setRole(userDetails.getRole());
        
        return userRepository.save(user);
    }
    
    public boolean deleteUser(Integer id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return false;
        }
        
        userRepository.delete(user);
        return true;
    }
    
    // Get available roles for registration (only MEMBER role for public registration)
    public List<Role> getAvailableRolesForRegistration() {
        List<Role> roles = new ArrayList<>();
        Role memberRole = new Role();
        memberRole.setId(4);
        memberRole.setName("MEMBER");
        roles.add(memberRole);
        return roles;
    }
}
