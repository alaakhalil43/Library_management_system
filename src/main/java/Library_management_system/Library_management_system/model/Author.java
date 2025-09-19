package Library_management_system.Library_management_system.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "authors")
@Data
public class Author {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "first_name",nullable = false)
    private String firstName;
    
    @Column(name = "last_name",nullable = false)
    private String lastName;

    @Column(name = "biography")
    private String biography;

}
