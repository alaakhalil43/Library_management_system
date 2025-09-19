package Library_management_system.Library_management_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import Library_management_system.Library_management_system.model.Author;
import Library_management_system.Library_management_system.model.User;
import Library_management_system.Library_management_system.repository.AuthorRepository;

@Service
public class AuthorService {
    
    @Autowired
    private AuthorRepository authorRepository;
    
    @Autowired
    private UserActivityLogService userActivityLogService;
    
    // Method to get current user from Security Context
    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            return (User) authentication.getPrincipal();
        }
        return null;
    }
    
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
    
    public Author getAuthorById(Integer id) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            return author.get();
        }
        return null;
    }
    
    public List<Author> getAuthorsByFirstName(String firstName) {
        return authorRepository.findByFirstNameContainingIgnoreCase(firstName);
    }
    
    public List<Author> getAuthorsByLastName(String lastName) {
        return authorRepository.findByLastNameContainingIgnoreCase(lastName);
    }
    
    public List<Author> getAuthorsByFullName(String name) {
        return authorRepository.findByFullNameContaining(name);
    }
    
    public List<Author> searchAuthorsByBiography(String biography) {
        return authorRepository.findByBiographyContainingIgnoreCase(biography);
    }
    
    public List<Author> searchAuthors(String searchTerm) {
        return authorRepository.findByFullNameContaining(searchTerm);
    }
    
    public Author saveAuthor(Author author) {
        // Check if author already exists
        List<Author> existingAuthors = authorRepository.findByFirstNameAndLastName(
            author.getFirstName(), author.getLastName());
        
        if (!existingAuthors.isEmpty()) {
            return null; // Author already exists
        }
        
        Author savedAuthor = authorRepository.save(author);
        
        // Log activity with current user
        User currentUser = getCurrentUser();
        userActivityLogService.logActivity(currentUser, "Added new author: " + author.getFirstName() + " " + author.getLastName());
        
        return savedAuthor;
    }
    
    public Author updateAuthor(Integer id, Author authorDetails) {
        Author author = authorRepository.findById(id).orElse(null);
        if (author == null) {
            return null;
        }
        
        author.setFirstName(authorDetails.getFirstName());
        author.setLastName(authorDetails.getLastName());
        author.setBiography(authorDetails.getBiography());
        
        Author updatedAuthor = authorRepository.save(author);
        
        // Log activity with current user
        User currentUser = getCurrentUser();
        userActivityLogService.logActivity(currentUser, "Updated author: " + author.getFirstName() + " " + author.getLastName());
        
        return updatedAuthor;
    }
    
    public boolean deleteAuthor(Integer id) {
        Author author = authorRepository.findById(id).orElse(null);
        if (author == null) {
            return false;
        }
        
        String authorName = author.getFirstName() + " " + author.getLastName();
        authorRepository.delete(author);
        
        // Log activity with current user
        User currentUser = getCurrentUser();
        userActivityLogService.logActivity(currentUser, "Deleted author: " + authorName);
        
        return true;
    }
}