package Library_management_system.Library_management_system.controller;

import Library_management_system.Library_management_system.model.Publisher;
import Library_management_system.Library_management_system.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publishers")
@CrossOrigin(origins = "*")
public class PublisherController {
    
    @Autowired
    private PublisherService publisherService;
    
    @GetMapping
    public List<Publisher> getAllPublishers() {
        return publisherService.getAllPublishers();
    }
    
    @GetMapping("/{id}")
    public Publisher getPublisherById(@PathVariable Integer id) {
        return publisherService.getPublisherById(id);
    }
    
    @GetMapping("/search")
    public List<Publisher> searchPublishers(@RequestParam String q) {
        return publisherService.searchPublishers(q);
    }
    
    @PostMapping
    public ResponseEntity<Publisher> createPublisher(@RequestBody Publisher publisher) {
        Publisher createdPublisher = publisherService.savePublisher(publisher);
        if (createdPublisher != null) {
            return new ResponseEntity<>(createdPublisher, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Publisher> updatePublisher(@PathVariable Integer id, @RequestBody Publisher publisherDetails) {
        Publisher updatedPublisher = publisherService.updatePublisher(id, publisherDetails);
        if (updatedPublisher != null) {
            return new ResponseEntity<>(updatedPublisher, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable Integer id) {
        boolean deleted = publisherService.deletePublisher(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
