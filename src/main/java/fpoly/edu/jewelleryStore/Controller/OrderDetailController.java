package fpoly.edu.jewelleryStore.Controller;

import fpoly.edu.jewelleryStore.Entity.OrderDetail;
import fpoly.edu.jewelleryStore.Service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orderdetails")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping
    public ResponseEntity<List<OrderDetail>> getAllOrderDetails() {
        List<OrderDetail> orderDetails = orderDetailService.findAll();
        return ResponseEntity.ok(orderDetails);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetail> getOrderDetailById(@PathVariable("id") Integer id) {
        OrderDetail orderDetail = orderDetailService.findById(id);
        if (orderDetail == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orderDetail);
    }

    @PostMapping
    public ResponseEntity<OrderDetail> createOrderDetail(@RequestBody OrderDetail orderDetail) {
        OrderDetail savedOrderDetail = orderDetailService.save(orderDetail);
        return ResponseEntity.ok(savedOrderDetail);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDetail> updateOrderDetail(@PathVariable("id") Integer id, @RequestBody OrderDetail orderDetail) {
        OrderDetail existingOrderDetail = orderDetailService.findById(id);
        if (existingOrderDetail == null) {
            return ResponseEntity.notFound().build();
        }
        orderDetail.setIdDetailOrder(id);
        OrderDetail updatedOrderDetail = orderDetailService.save(orderDetail);
        return ResponseEntity.ok(updatedOrderDetail);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderDetail(@PathVariable("id") Integer id) {
        OrderDetail existingOrderDetail = orderDetailService.findById(id);
        if (existingOrderDetail == null) {
            return ResponseEntity.notFound().build();
        }
        orderDetailService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // API phân trang
    @GetMapping("/page")
    public ResponseEntity<Page<OrderDetail>> getPaginatedOrderDetails(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "idDetailOrder,asc") String[] sort) {

        Sort.Direction direction = Sort.Direction.fromString(sort[1]);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort[0]));
        Page<OrderDetail> orderDetailPage = orderDetailService.findPaginated(pageable);
        return ResponseEntity.ok(orderDetailPage);
    }
}
