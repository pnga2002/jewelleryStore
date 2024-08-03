package fpoly.edu.jewelleryStore.Service;

import fpoly.edu.jewelleryStore.Entity.Orders;
import fpoly.edu.jewelleryStore.Entity.Product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    ResponseEntity<List<Product>> findAll();
    ResponseEntity<List<Product>> findSearch(String key);
    ResponseEntity<Product> findById(Integer id);
    ResponseEntity<Product> save(Product product);
    ResponseEntity<Void> deleteById(Integer id);
    ResponseEntity<Page<Product>> findPaginated(Pageable pageable);

    ResponseEntity<List<Product>> findByIdCaategory(Integer id);
}
