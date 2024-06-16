package fpoly.edu.jewelleryStore.Controller;

import fpoly.edu.jewelleryStore.Entity.CartDetail;
import fpoly.edu.jewelleryStore.EntityViewModel.AddCartVM;
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
        return cartDetailService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartDetail> getCartDetailById(@PathVariable("id") Integer id) {
        return cartDetailService.findById(id);
    }

    @PostMapping
    public ResponseEntity<CartDetail> createCartDetail(@RequestBody AddCartVM savedCartDetail) {
        return cartDetailService.save(savedCartDetail);
    }

	/*
	 * @PutMapping("/{id}") public ResponseEntity<CartDetail>
	 * updateCartDetail(@PathVariable("id") Integer id, @RequestBody CartDetail
	 * cartDetail) { return cartDetailService.save(cartDetail); }
	 */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartDetail(@PathVariable("id") Integer id) {
        return cartDetailService.deleteById(id);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CartDetail>> getCartDetailsByCartIdAndUserId(
            @PathVariable("userId") Integer userId) {
        
        return cartDetailService.findByUserId(userId);
    }
}
