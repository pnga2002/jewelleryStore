package fpoly.edu.jewelleryStore.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
@Setter
@Getter
@Data
@Entity
@Table(name="Product")
public class Product {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "idproduct")
	    private Integer idProduct;

	    @Column(name = "name", nullable = false, length = 255)
	    private String name;

	    @Column(name = "description")
	    private String description;

	    @Column(name = "price")
	    private float price;

	    @Column(name = "imageurl", length = 255)
	    private String imageUrl;

	    @ManyToOne
	    @JoinColumn(name = "idcategory", referencedColumnName = "idcategory")
	    private Category category;
}
