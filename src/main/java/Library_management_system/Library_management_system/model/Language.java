package Library_management_system.Library_management_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "languages")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Language {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotBlank(message = "Language name is required")
    @Size(min = 2, max = 50, message = "Language name must be between 2 and 50 characters")
    @Column(unique = true, nullable = false)
    private String name;
    
    @OneToMany(mappedBy = "language", cascade = CascadeType.ALL)
    private List<Book> books;
}
