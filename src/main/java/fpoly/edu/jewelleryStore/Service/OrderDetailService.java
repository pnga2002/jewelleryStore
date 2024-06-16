package fpoly.edu.jewelleryStore.Service;

import fpoly.edu.jewelleryStore.Entity.OrderDetail;
import fpoly.edu.jewelleryStore.EntityViewModel.OrderViewModel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderDetailService {

    ResponseEntity<List<OrderDetail>> findAll();

    ResponseEntity<OrderDetail> findById(Integer id);

    ResponseEntity<String> save(OrderViewModel orderDetail);

    ResponseEntity<Void> deleteById(Integer id);

    ResponseEntity<Page<OrderDetail>> findPaginated(Pageable pageable);

    ResponseEntity<List<OrderDetail>> findByOrderId(Integer id);
}
