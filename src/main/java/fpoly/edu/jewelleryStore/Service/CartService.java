package fpoly.edu.jewelleryStore.Service;

import fpoly.edu.jewelleryStore.Entity.Carts;
import java.util.List;

import org.springframework.http.ResponseEntity;

public interface CartService {
	ResponseEntity<List<Carts>> findAll();
	ResponseEntity<Carts> findById(Integer id);
	ResponseEntity<Carts> save(Carts cart);
	ResponseEntity<Void> deleteById(Integer id);
}
