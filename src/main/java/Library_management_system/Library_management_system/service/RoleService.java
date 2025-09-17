package Library_management_system.Library_management_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Library_management_system.Library_management_system.model.Role;
import Library_management_system.Library_management_system.repository.RoleRepository;

@Service
public class RoleService {
    
    @Autowired
    private RoleRepository roleRepository;
    
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
    
    public Role getRoleById(Integer id) {
        Optional<Role> role = roleRepository.findById(id);
        if (role.isPresent()) {
            return role.get();
        }
        return null;
    }
    
    public Role getRoleByName(String name) {
        Optional<Role> role = roleRepository.findByName(name);
        if (role.isPresent()) {
            return role.get();
        }
        return null;
    }
    
    public List<Role> searchRoles(String searchTerm) {
        return roleRepository.findByNameContaining(searchTerm);
    }
    
    public Role saveRole(Role role) {
        if (roleRepository.existsByName(role.getName())) {
            return null; // Role name already exists
        }
        return roleRepository.save(role);
    }
    
    public Role updateRole(Integer id, Role roleDetails) {
        Role role = roleRepository.findById(id).orElse(null);
        if (role == null) {
            return null;
        }
        
        // Check if name is being changed and if new name already exists
        if (!role.getName().equals(roleDetails.getName()) && 
            roleRepository.existsByName(roleDetails.getName())) {
            return null; // Role name already exists
        }
        
        role.setName(roleDetails.getName());
        
        return roleRepository.save(role);
    }
    
    public boolean deleteRole(Integer id) {
        Role role = roleRepository.findById(id).orElse(null);
        if (role == null) {
            return false;
        }
        
        roleRepository.delete(role);
        return true;
    }
}