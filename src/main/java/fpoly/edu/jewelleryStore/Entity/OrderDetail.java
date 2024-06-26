package fpoly.edu.jewelleryStore.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "[Orderdetail]")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddetailorder")
    private Integer idDetailOrder;

    @ManyToOne
    @JoinColumn(name = "idorder", referencedColumnName = "idorder", nullable = false)
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "idproduct", referencedColumnName = "idproduct", nullable = false)
    private Product product;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Float price;
}
