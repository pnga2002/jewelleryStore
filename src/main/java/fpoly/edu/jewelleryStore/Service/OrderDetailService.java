package fpoly.edu.jewelleryStore.Service;

import fpoly.edu.jewelleryStore.Entity.OrderDetail;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface OrderDetailService {
    List<OrderDetail> findAll();
    OrderDetail findById(Integer id);
    OrderDetail save(OrderDetail orderDetail);
    void deleteById(Integer id);
    Page<OrderDetail> findPaginated(Pageable pageable); 
}
