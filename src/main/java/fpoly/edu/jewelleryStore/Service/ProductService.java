package fpoly.edu.jewelleryStore.Service;

import fpoly.edu.jewelleryStore.Entity.Product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    List<Product> findAll();
    Product findById(Integer id);
    Product save(Product product);
    void deleteById(Integer id);
    Page<Product> findPaginated(Pageable pageable);
}
