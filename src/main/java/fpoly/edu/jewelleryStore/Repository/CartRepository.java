package fpoly.edu.jewelleryStore.Repository;

import fpoly.edu.jewelleryStore.Entity.Carts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Carts, Integer> {
    //
}
