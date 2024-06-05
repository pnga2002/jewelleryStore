package fpoly.edu.jewelleryStore.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="Category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCategory")
    private Integer idCategory;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    // Constructors, getters, and setters can be omitted as they are generated by Lombok's @Data annotation
}