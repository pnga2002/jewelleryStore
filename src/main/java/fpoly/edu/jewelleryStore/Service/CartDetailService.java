package fpoly.edu.jewelleryStore.Service;

import fpoly.edu.jewelleryStore.Entity.CartDetail;
import java.util.List;

public interface CartDetailService {
    List<CartDetail> findAll();
    CartDetail findById(Integer id);
    CartDetail save(CartDetail cartDetail);
    void deleteById(Integer id);
    List<CartDetail> findByCartIdAndUserId(Integer cartId, Integer userId);
}
