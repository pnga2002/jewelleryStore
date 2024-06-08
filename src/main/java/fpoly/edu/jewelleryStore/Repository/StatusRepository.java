package fpoly.edu.jewelleryStore.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fpoly.edu.jewelleryStore.Entity.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {
	//
}
