package Library_management_system.Library_management_system.controller;

import Library_management_system.Library_management_system.model.Language;
import Library_management_system.Library_management_system.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/languages")
@CrossOrigin(origins = "*")
public class LanguageController {
    
    @Autowired
    private LanguageService languageService;
    
    @GetMapping
    public List<Language> getAllLanguages() {
        return languageService.getAllLanguages();
    }
    
    @GetMapping("/{id}")
    public Language getLanguageById(@PathVariable Integer id) {
        return languageService.getLanguageById(id);
    }
    
    @GetMapping("/name/{name}")
    public Language getLanguageByName(@PathVariable String name) {
        return languageService.getLanguageByName(name);
    }

    @PostMapping
    public ResponseEntity<Language> createLanguage(@RequestBody Language language) {
        Language createdLanguage = languageService.saveLanguage(language);
        if (createdLanguage != null) {
            return new ResponseEntity<>(createdLanguage, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Language> updateLanguage(@PathVariable Integer id, @RequestBody Language languageDetails) {
        Language updatedLanguage = languageService.updateLanguage(id, languageDetails);
        if (updatedLanguage != null) {
            return new ResponseEntity<>(updatedLanguage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLanguage(@PathVariable Integer id) {
        boolean deleted = languageService.deleteLanguage(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
