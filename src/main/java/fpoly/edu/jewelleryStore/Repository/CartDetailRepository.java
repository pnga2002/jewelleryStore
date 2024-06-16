package fpoly.edu.jewelleryStore.Repository;

import fpoly.edu.jewelleryStore.Entity.CartDetail;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Integer> {
    List<CartDetail> findByCart_AppUser_IdUser(Integer userId);
    
    Optional<CartDetail> findByCart_IdCartAndProduct_IdProduct(Integer cartId, Integer productId);
}
