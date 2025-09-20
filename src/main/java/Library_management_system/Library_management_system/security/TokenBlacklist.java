package Library_management_system.Library_management_system.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class TokenBlacklist {
    
    private Set<String> blacklistedTokens = new HashSet<>();
    
    public void addToBlacklist(String token) {
        blacklistedTokens.add(token);
    }
    
    public boolean isBlacklisted(String token) {
        return blacklistedTokens.contains(token);
    }
    
    public void removeFromBlacklist(String token) {
        blacklistedTokens.remove(token);
    }
    
    public void clearBlacklist() {
        blacklistedTokens.clear();
    }
}
