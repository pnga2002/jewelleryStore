package fpoly.edu.jewelleryStore.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fpoly.edu.jewelleryStore.Entity.Orders;
import fpoly.edu.jewelleryStore.Service.OrderService;

@RestController
@RequestMapping("api/order")
public class OrderController {
	@Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Orders>> getAllUsers() {
        List<Orders> res = orderService.findAll();
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orders> getUserById(@PathVariable("id") Integer id) {
    	Orders res = orderService.findById(id);
        if (res == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(res);
    }

    @PostMapping
    public ResponseEntity<Orders> createUser(@RequestBody Orders model) {
    	Orders savedOrder = orderService.save(model);
        return ResponseEntity.ok(savedOrder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orders> updateUser(@PathVariable("id") Integer id, @RequestBody Orders model) {
    	Orders existing = orderService.findById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        model.setIdOrder(id);
        Orders updated = orderService.save(model);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id) {
    	Orders existingUser = orderService.findById(id);
        if (existingUser == null) {
            return ResponseEntity.notFound().build();
        }
        orderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // API phân trang
    @GetMapping("/page")
    public ResponseEntity<Page<Orders>> getPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "idUser,asc") String[] sort) {

        Sort.Direction direction = Sort.Direction.fromString(sort[1]);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort[0]));
        Page<Orders> orderPage = orderService.findPaginated(pageable);
        return ResponseEntity.ok(orderPage);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Orders>> getOrdersByUserId(@PathVariable("userId") Integer userId) {
        List<Orders> orders = orderService.findByIdUser(userId);
        if (orders.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orders);
    }
}

