package fpoly.edu.jewelleryStore.Controller;

import fpoly.edu.jewelleryStore.Entity.Carts;
import fpoly.edu.jewelleryStore.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartService cartsService;

    @GetMapping
    public ResponseEntity<List<Carts>> getAllCarts() {
        return cartsService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carts> getCartById(@PathVariable("id") Integer id) {
        return cartsService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Carts> createCart(@RequestBody Carts cart) {
        return cartsService.save(cart);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carts> updateCart(@PathVariable("id") Integer id, @RequestBody Carts cart) {
        return cartsService.save(cart);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable("id") Integer id) {
        return cartsService.deleteById(id);

    }
}
