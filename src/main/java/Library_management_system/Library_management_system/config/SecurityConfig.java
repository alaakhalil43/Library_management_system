package Library_management_system.Library_management_system.config;
import Library_management_system.Library_management_system.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    
    @Autowired
    private CustomUserDetailsService userDetailsService;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                // Public endpoints
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers("/actuator/**").permitAll()
                
                // Book endpoints - different permissions based on role
                .requestMatchers("/api/books/**").hasAnyRole("ADMINISTRATOR", "LIBRARIAN", "STAFF")
                
                // Author endpoints - different permissions based on role
                .requestMatchers("/api/authors/**").hasAnyRole("ADMINISTRATOR", "LIBRARIAN", "STAFF")
                
                // Member endpoints - different permissions based on role
                .requestMatchers("/api/members/**").hasAnyRole("ADMINISTRATOR", "LIBRARIAN", "STAFF")
                
                // Borrowing endpoints - different permissions based on role
                .requestMatchers("/api/borrowing/**").hasAnyRole("ADMINISTRATOR", "LIBRARIAN", "STAFF")
                
                // Public endpoints (no authentication required)
                .requestMatchers("/api/users/register").permitAll()
                .requestMatchers("/api/users/available-roles").permitAll()
                
                // Admin only endpoints
                .requestMatchers("/api/admin/**").hasRole("ADMINISTRATOR")
                .requestMatchers("/api/users/**").hasAnyRole("ADMINISTRATOR", "LIBRARIAN")
                .requestMatchers("/api/roles/**").hasRole("ADMINISTRATOR")
                
                // Activity logs endpoints - Admin/Librarian only
                .requestMatchers("/api/activity-logs/**").hasAnyRole("ADMINISTRATOR", "LIBRARIAN")
                
                // All other requests need authentication
                .anyRequest().authenticated()
            )
            .headers(headers -> headers.frameOptions().disable()) // For H2 console
            .authenticationProvider(authenticationProvider());
        
        return http.build();
    }
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
