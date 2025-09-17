package Library_management_system.Library_management_system.service;

import Library_management_system.Library_management_system.model.Language;
import Library_management_system.Library_management_system.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {
    
    @Autowired
    private LanguageRepository languageRepository;
    
    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }
    
    public Language getLanguageById(Integer id) {
        Optional<Language> language = languageRepository.findById(id);
        if (language.isPresent()) {
            return language.get();
        }
        return null;
    }
    
    public Language getLanguageByName(String name) {
        Optional<Language> language = languageRepository.findByName(name);
        if (language.isPresent()) {
            return language.get();
        }
        return null;
    }
    
    
    public List<Language> searchLanguages(String searchTerm) {
        return languageRepository.findByNameContainingIgnoreCase(searchTerm);
    }
    
    public Language saveLanguage(Language language) {
        if (languageRepository.existsByName(language.getName())) {
            return null; // Language name already exists
        }
        return languageRepository.save(language);
    }
    
    public Language updateLanguage(Integer id, Language languageDetails) {
        Language language = languageRepository.findById(id).orElse(null);
        if (language == null) {
            return null;
        }
        
        language.setName(languageDetails.getName());
        
        return languageRepository.save(language);
    }
    
    public boolean deleteLanguage(Integer id) {
        Language language = languageRepository.findById(id).orElse(null);
        if (language == null) {
            return false;
        }
        
        languageRepository.delete(language);
        return true;
    }
}
