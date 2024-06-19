package fpoly.edu.jewelleryStore.Repository;

import fpoly.edu.jewelleryStore.Entity.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // Bạn có thể thêm các phương thức tùy chỉnh ở đây nếu cần
	@Query(value = "SELECT * FROM product WHERE idproduct = :id", nativeQuery = true)
    Product findByIdProduct(int id);
//	@Query(value = "SELECT * FROM product p WHERE p.category.idcategory = :id", nativeQuery = true)
    List<Product> findByCategoryIdCategory(int id);
}

