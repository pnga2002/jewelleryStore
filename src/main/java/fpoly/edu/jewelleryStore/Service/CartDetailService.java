package fpoly.edu.jewelleryStore.Service;

import fpoly.edu.jewelleryStore.Entity.CartDetail;
import fpoly.edu.jewelleryStore.EntityViewModel.AddCartVM;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CartDetailService {
    ResponseEntity<List<CartDetail>> findAll();
    ResponseEntity<CartDetail> findById(Integer id);
    ResponseEntity<CartDetail> save(AddCartVM model);
    ResponseEntity<Void> deleteById(Integer id);
    ResponseEntity<List<CartDetail>> findByUserId(Integer userId);
}
