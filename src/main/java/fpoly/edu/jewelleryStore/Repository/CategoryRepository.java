package fpoly.edu.jewelleryStore.Repository;

import fpoly.edu.jewelleryStore.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	//
}
