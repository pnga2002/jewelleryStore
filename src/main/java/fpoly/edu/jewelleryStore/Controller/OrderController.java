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
import fpoly.edu.jewelleryStore.EntityViewModel.ListOrderViewModel;
import fpoly.edu.jewelleryStore.EntityViewModel.UpdateStatusOrder;
import fpoly.edu.jewelleryStore.Service.OrderService;

@RestController
@RequestMapping("api/order")
public class OrderController {
	@Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Orders>> getAllUsers() {
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orders> getUserById(@PathVariable("id") Integer id) {
        return orderService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Orders> createUser(@RequestBody Orders model) {
        return orderService.save(model);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orders> updateUser(@PathVariable("id") Integer id, @RequestBody Orders model) {

        return orderService.save(model);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Integer id) {

        return orderService.deleteById(id);
    }

    // API phân trang
    @GetMapping("/page")
    public ResponseEntity<Page<Orders>> getPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "idUser,asc") String[] sort) {

        Sort.Direction direction = Sort.Direction.fromString(sort[1]);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort[0]));
        return orderService.findPaginated(pageable);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ListOrderViewModel>> getOrdersByUserId(@PathVariable("userId") Integer userId) {
        return orderService.findByIdUser(userId);
    } 
    @PutMapping("/update")
    public ResponseEntity<String> updateStatusOrder(@RequestBody UpdateStatusOrder model) {
        return orderService.updateStatusOrder(model);
    }
}

