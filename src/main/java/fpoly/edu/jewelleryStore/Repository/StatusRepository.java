package fpoly.edu.jewelleryStore.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fpoly.edu.jewelleryStore.Entity.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {
	//
	@Query(value = "SELECT * FROM Status WHERE name LIKE CONCAT('%', :key, '%')", nativeQuery = true)
    List<Status> findByKey(@Param("key") String key);
}
