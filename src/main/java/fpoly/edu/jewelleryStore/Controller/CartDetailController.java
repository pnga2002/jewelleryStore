package fpoly.edu.jewelleryStore.Controller;

import fpoly.edu.jewelleryStore.Entity.CartDetail;
import fpoly.edu.jewelleryStore.Service.CartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart-details")
public class CartDetailController {

    @Autowired
    private CartDetailService cartDetailService;

    @GetMapping
    public ResponseEntity<List<CartDetail>> getAllCartDetails() {
        List<CartDetail> cartDetails = cartDetailService.findAll();
        return ResponseEntity.ok(cartDetails);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartDetail> getCartDetailById(@PathVariable("id") Integer id) {
        CartDetail cartDetail = cartDetailService.findById(id);
        if (cartDetail == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cartDetail);
    }

    @PostMapping
    public ResponseEntity<CartDetail> createCartDetail(@RequestBody CartDetail cartDetail) {
        CartDetail savedCartDetail = cartDetailService.save(cartDetail);
        return ResponseEntity.ok(savedCartDetail);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartDetail> updateCartDetail(@PathVariable("id") Integer id, @RequestBody CartDetail cartDetail) {
        CartDetail existingCartDetail = cartDetailService.findById(id);
        if (existingCartDetail == null) {
            return ResponseEntity.notFound().build();
        }
        cartDetail.setIdCartDetail(id);
        CartDetail updatedCartDetail = cartDetailService.save(cartDetail);
        return ResponseEntity.ok(updatedCartDetail);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartDetail(@PathVariable("id") Integer id) {
        CartDetail existingCartDetail = cartDetailService.findById(id);
        if (existingCartDetail == null) {
            return ResponseEntity.notFound().build();
        }
        cartDetailService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/cart/{cartId}/user/{userId}")
    public ResponseEntity<List<CartDetail>> getCartDetailsByCartIdAndUserId(
            @PathVariable("cartId") Integer cartId,
            @PathVariable("userId") Integer userId) {
        List<CartDetail> cartDetails = cartDetailService.findByCartIdAndUserId(cartId, userId);
        if (cartDetails.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cartDetails);
    }
}
