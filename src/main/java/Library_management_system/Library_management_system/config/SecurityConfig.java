package Library_management_system.Library_management_system.config;
import Library_management_system.Library_management_system.service.CustomUserDetailsService;
import Library_management_system.Library_management_system.security.JwtAuthenticationFilter;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    
    @Autowired
    private CustomUserDetailsService userDetailsService;
    
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    
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
        AuthenticationManager authManager = config.getAuthenticationManager();
        return authManager;
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                // Public endpoints
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers("/actuator/**").permitAll()
                

                .requestMatchers("/api/books").hasAnyRole("MEMBER", "STAFF", "LIBRARIAN", "ADMINISTRATOR")
                .requestMatchers("/api/books/**").hasAnyRole("MEMBER", "STAFF", "LIBRARIAN", "ADMINISTRATOR")
                

                .requestMatchers("/api/authors").hasAnyRole("MEMBER", "STAFF", "LIBRARIAN", "ADMINISTRATOR")
                .requestMatchers("/api/authors/**").hasAnyRole("MEMBER", "STAFF", "LIBRARIAN", "ADMINISTRATOR")
                

                .requestMatchers("/api/members/**").hasAnyRole("STAFF", "LIBRARIAN", "ADMINISTRATOR")
                

                .requestMatchers("/api/borrowing/**").hasAnyRole("MEMBER", "STAFF", "LIBRARIAN", "ADMINISTRATOR")
                
                // Public endpoints
                .requestMatchers("/api/users/register").permitAll()
                .requestMatchers("/api/users/available-roles").permitAll()
                
                // Admin only endpoints
                .requestMatchers("/api/admin/**").hasRole("ADMINISTRATOR")
                .requestMatchers("/api/users/**").hasRole("ADMINISTRATOR")
                .requestMatchers("/api/roles/**").hasRole("ADMINISTRATOR")
                
                // Activity logs endpoints - Admin/Librarian only
                .requestMatchers("/api/activity-logs/**").hasAnyRole("ADMINISTRATOR", "LIBRARIAN")


                .anyRequest().authenticated()
            )
            .headers(headers -> headers.frameOptions().disable()) // For H2 console
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .authenticationProvider(authenticationProvider())
            .httpBasic(httpBasic -> httpBasic.disable());
        
        return http.build();
    }
    
}
