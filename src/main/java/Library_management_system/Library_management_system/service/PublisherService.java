package Library_management_system.Library_management_system.service;

import Library_management_system.Library_management_system.model.Publisher;
import Library_management_system.Library_management_system.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {
    
    @Autowired
    private PublisherRepository publisherRepository;
    
    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }
    
    public Publisher getPublisherById(Integer id) {
        Optional<Publisher> publisher = publisherRepository.findById(id);
        if (publisher.isPresent()) {
            return publisher.get();
        }
        return null;
    }
    
    public List<Publisher> searchPublishers(String searchTerm) {
        return publisherRepository.findByNameContainingIgnoreCase(searchTerm);
    }
    
    public Publisher savePublisher(Publisher publisher) {
        return publisherRepository.save(publisher);
    }
    
    public Publisher updatePublisher(Integer id, Publisher publisherDetails) {
        Publisher publisher = publisherRepository.findById(id).orElse(null);
        if (publisher == null) {
            return null;
        }
        
        publisher.setName(publisherDetails.getName());
        publisher.setDescription(publisherDetails.getDescription());
        
        return publisherRepository.save(publisher);
    }
    
    public boolean deletePublisher(Integer id) {
        Publisher publisher = publisherRepository.findById(id).orElse(null);
        if (publisher == null) {
            return false;
        }
        
        publisherRepository.delete(publisher);
        return true;
    }
}
