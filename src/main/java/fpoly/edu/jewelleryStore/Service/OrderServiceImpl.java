package fpoly.edu.jewelleryStore.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fpoly.edu.jewelleryStore.Entity.Orders;
import fpoly.edu.jewelleryStore.Repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Orders> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Orders findById(Integer id) {
        Optional<Orders> optional = orderRepository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public Orders save(Orders model) {
        return orderRepository.save(model);
    }

    @Override
    public void deleteById(Integer id) {
    	orderRepository.deleteById(id);
    }

    @Override
    public Page<Orders> findPaginated(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }
    @Override
    public List<Orders> findByIdUser(Integer idUser){
    	return orderRepository.findByUserIdOrderByOrderId(idUser);
    }
}
