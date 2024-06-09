package fpoly.edu.jewelleryStore.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fpoly.edu.jewelleryStore.Entity.Orders;

public interface OrderService {
	List<Orders> findAll();

	Orders findById(Integer id);

	Orders save(Orders model);

	void deleteById(Integer id);

	Page<Orders> findPaginated(Pageable pageable);

	List<Orders> findByIdUser(Integer idUser);
}
