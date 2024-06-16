package fpoly.edu.jewelleryStore.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import fpoly.edu.jewelleryStore.Entity.Orders;
import fpoly.edu.jewelleryStore.EntityViewModel.ListOrderViewModel;

public interface OrderService {
	ResponseEntity<List<Orders>> findAll();

	ResponseEntity<Orders> findById(Integer id);

	ResponseEntity<Orders> save(Orders model);

	ResponseEntity<String> deleteById(Integer id);

	ResponseEntity<Page<Orders>> findPaginated(Pageable pageable);

	ResponseEntity<List<ListOrderViewModel>> findByIdUser(Integer idUser);

	
}
