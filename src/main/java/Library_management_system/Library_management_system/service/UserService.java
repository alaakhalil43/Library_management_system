package Library_management_system.Library_management_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Library_management_system.Library_management_system.model.Role;
import Library_management_system.Library_management_system.model.User;
import Library_management_system.Library_management_system.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public User getUserById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }
    
    public User getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }
    
    public User getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }
    
    public List<User> searchUsers(String searchTerm) {
        return userRepository.findByNameContaining(searchTerm);
    }
    
    public List<User> getUsersByRole(Role role) {
        return userRepository.findByRole(role);
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
}
