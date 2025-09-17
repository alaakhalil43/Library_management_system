package Library_management_system.Library_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Library_management_system.Library_management_system.model.Category;
import Library_management_system.Library_management_system.repository.CategoryRepository;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    // Get all categories
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    
    // Get category by ID
    public Category getCategoryById(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }
    
    // Save category
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }
    
    // Update category
    public Category updateCategory(Integer id, Category categoryDetails) {
        Category category = getCategoryById(id);
        if (category == null) {
            return null;
        }
        
        category.setName(categoryDetails.getName());
        category.setDescription(categoryDetails.getDescription());
        category.setParent(categoryDetails.getParent());
        
        return categoryRepository.save(category);
    }
    
    // Delete category
    public boolean deleteCategory(Integer id) {
        Category category = getCategoryById(id);
        if (category == null) {
            return false;
        }
        
        categoryRepository.delete(category);
        return true;
    }
}