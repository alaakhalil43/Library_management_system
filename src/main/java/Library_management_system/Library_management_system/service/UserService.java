package Library_management_system.Library_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Library_management_system.Library_management_system.model.User;
import Library_management_system.Library_management_system.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    // Get user by ID
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }
    
    // Get user by username
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
    
    // Save user
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    
    // Update user
    public User updateUser(Integer id, User userDetails) {
        User user = getUserById(id);
        if (user == null) {
            return null;
        }
        
        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setAddress(userDetails.getAddress());
        user.setRole(userDetails.getRole());
        
        return userRepository.save(user);
    }
    
    // Delete user
    public boolean deleteUser(Integer id) {
        User user = getUserById(id);
        if (user == null) {
            return false;
        }
        
        userRepository.delete(user);
        return true;
    }
}