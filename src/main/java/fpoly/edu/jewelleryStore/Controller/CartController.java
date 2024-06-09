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
        List<Carts> carts = cartsService.findAll();
        return ResponseEntity.ok(carts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carts> getCartById(@PathVariable("id") Integer id) {
        Carts cart = cartsService.findById(id);
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cart);
    }

    @PostMapping
    public ResponseEntity<Carts> createCart(@RequestBody Carts cart) {
        Carts savedCart = cartsService.save(cart);
        return ResponseEntity.ok(savedCart);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carts> updateCart(@PathVariable("id") Integer id, @RequestBody Carts cart) {
        Carts existingCart = cartsService.findById(id);
        if (existingCart == null) {
            return ResponseEntity.notFound().build();
        }
        cart.setIdCart(id);
        Carts updatedCart = cartsService.save(cart);
        return ResponseEntity.ok(updatedCart);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable("id") Integer id) {
        Carts existingCart = cartsService.findById(id);
        if (existingCart == null) {
            return ResponseEntity.notFound().build();
        }
        cartsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
