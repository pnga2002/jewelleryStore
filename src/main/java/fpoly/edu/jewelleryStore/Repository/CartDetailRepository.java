package fpoly.edu.jewelleryStore.Repository;

import fpoly.edu.jewelleryStore.Entity.CartDetail;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Integer> {
    List<CartDetail> findByCart_IdCartAndCart_AppUser_IdUser(Integer cartId, Integer userId);

}
