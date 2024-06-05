package fpoly.edu.jewelleryStore.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name="Product")
public class Product {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "idProduct")
	    private Integer idProduct;

	    @Column(name = "name", nullable = false, length = 255)
	    private String name;

	    @Column(name = "description", length = 255)
	    private String description;

	    @Column(name = "price")
	    private float price;

	    @Column(name = "imageUrl", length = 255)
	    private String imageUrl;

	    @ManyToOne
	    @JoinColumn(name = "idCategory", referencedColumnName = "idCategory")
	    private Category category;
}
