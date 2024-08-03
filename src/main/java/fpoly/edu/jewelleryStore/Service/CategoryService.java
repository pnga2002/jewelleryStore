package fpoly.edu.jewelleryStore.Service;

import fpoly.edu.jewelleryStore.Entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {
	ResponseEntity<List<Category>> findAll();
	ResponseEntity<Category> findById(Integer id);
	ResponseEntity<Category> save(Category category);
	ResponseEntity<Void> deleteById(Integer id);
	ResponseEntity<Page<Category>> findPaginated(Pageable pageable);

	ResponseEntity<List<Category>> findSearch(String key);
}
