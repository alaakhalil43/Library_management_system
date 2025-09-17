package Library_management_system.Library_management_system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotBlank(message = "Role name is required")
    @Size(min = 2, max = 20, message = "Role name must be between 2 and 20 characters")
    @Column(unique = true, nullable = false)
    private String name;
    
    @OneToOne(mappedBy = "role", fetch = jakarta.persistence.FetchType.EAGER)
    @JsonIgnore
    private User user;
}
