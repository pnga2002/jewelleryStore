package fpoly.edu.jewelleryStore.Repository;

import fpoly.edu.jewelleryStore.Entity.Category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	//
	@Query(value = "SELECT * FROM Category WHERE name LIKE CONCAT('%', :key, '%')", nativeQuery = true)
    List<Category> findByKey(@Param("key") String key);
}
