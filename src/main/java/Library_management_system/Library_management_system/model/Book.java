package Library_management_system.Library_management_system.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "books")
@Data
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "isbn",nullable = false)
    private String isbn;

    @Column(name = "publication_year",nullable = false)
    private Integer publicationYear;

    @Column(name = "edition")
    private String edition;

    @Column(name = "summary")
    private String summary;
    
    @Column(name = "cover_image_url")
    private String coverImageUrl;
    
    @Column(name = "total_copies",nullable = false)
    private Integer totalCopies;
    
    @Column(name = "available_copies",nullable = false)
    private Integer availableCopies;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "language_id")
    private Language language;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors;
}
