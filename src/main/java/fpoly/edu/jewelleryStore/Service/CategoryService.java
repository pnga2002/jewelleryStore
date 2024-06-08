package fpoly.edu.jewelleryStore.Service;

import fpoly.edu.jewelleryStore.Entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findById(Integer id);
    Category save(Category category);
    void deleteById(Integer id);
    Page<Category> findPaginated(Pageable pageable);
}
