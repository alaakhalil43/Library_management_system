package Library_management_system.Library_management_system.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Library_management_system.Library_management_system.model.Author;
import Library_management_system.Library_management_system.service.AuthorService;

@RestController
@RequestMapping("/api/authors")
@CrossOrigin(origins = "*")
public class AuthorController {
    
    @Autowired
    private AuthorService authorService;
    
    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }
    
    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable Integer id) {
        return authorService.getAuthorById(id);
    }
    
    @GetMapping("/first-name")
    public List<Author> getAuthorsByFirstName(@RequestParam String firstName) {
        return authorService.getAuthorsByFirstName(firstName);
    }
    
    @GetMapping("/last-name")
    public List<Author> getAuthorsByLastName(@RequestParam String lastName) {
        return authorService.getAuthorsByLastName(lastName);
    }
    
    @GetMapping("/full-name")
    public List<Author> getAuthorsByFullName(@RequestParam String name) {
        return authorService.getAuthorsByFullName(name);
    }
    
    @GetMapping("/search")
    public List<Author> searchAuthors(@RequestParam String q) {
        return authorService.searchAuthors(q);
    }
    
    @GetMapping("/biography")
    public List<Author> searchAuthorsByBiography(@RequestParam String biography) {
        return authorService.searchAuthorsByBiography(biography);
    }
    
    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        Author createdAuthor = authorService.saveAuthor(author);
        if (createdAuthor != null) {
            return new ResponseEntity<>(createdAuthor, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Integer id, @RequestBody Author authorDetails) {
        Author updatedAuthor = authorService.updateAuthor(id, authorDetails);
        if (updatedAuthor != null) {
            return new ResponseEntity<>(updatedAuthor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Integer id) {
        boolean deleted = authorService.deleteAuthor(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}