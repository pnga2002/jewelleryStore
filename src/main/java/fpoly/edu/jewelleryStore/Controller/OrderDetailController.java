package fpoly.edu.jewelleryStore.Controller;

import fpoly.edu.jewelleryStore.Entity.OrderDetail;
import fpoly.edu.jewelleryStore.EntityViewModel.OrderViewModel;
import fpoly.edu.jewelleryStore.EntityViewModel.ThanhToanViewModel;
import fpoly.edu.jewelleryStore.Service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orderdetails")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping
    public ResponseEntity<List<OrderDetail>> getAllOrderDetails() {
        return orderDetailService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetail> getOrderDetailById(@PathVariable("id") Integer id) {
        return orderDetailService.findById(id);
    }

    @PostMapping
    public ResponseEntity<String> createOrderDetail(@RequestBody OrderViewModel orderDetail) {
        return orderDetailService.save(orderDetail);
    }

    @PostMapping("thanhToan")
    public ResponseEntity<String> createOrderDetailThanhToan(@RequestBody ThanhToanViewModel orderDetail) throws IOException {
        return orderDetailService.payPackage(orderDetail);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderDetail(@PathVariable("id") Integer id) {
        return orderDetailService.deleteById(id);
    }

    // API phân trang
    @GetMapping("/page")
    public ResponseEntity<Page<OrderDetail>> getPaginatedOrderDetails(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "idDetailOrder,asc") String[] sort) {

        Sort.Direction direction = Sort.Direction.fromString(sort[1]);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort[0]));
        return orderDetailService.findPaginated(pageable);
    }
    @GetMapping("order/{id}")
    public ResponseEntity<List<OrderDetail>> getOrderDetailByOrderId(@PathVariable("id") Integer id) {
        return orderDetailService.findByOrderId(id);
    }
}
