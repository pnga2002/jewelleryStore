package fpoly.edu.jewelleryStore.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fpoly.edu.jewelleryStore.Entity.OrderDetail;
import fpoly.edu.jewelleryStore.Entity.Orders;
import fpoly.edu.jewelleryStore.Entity.Status;
import fpoly.edu.jewelleryStore.EntityViewModel.ListOrderViewModel;
import fpoly.edu.jewelleryStore.EntityViewModel.UpdateStatusOrder;
import fpoly.edu.jewelleryStore.Repository.OrderDetailRepository;
import fpoly.edu.jewelleryStore.Repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public ResponseEntity<List<Orders>> findAll() {
        try {
            List<Orders> orders = orderRepository.findAll();
            if (orders.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Orders> findById(Integer id) {
        Optional<Orders> optional = orderRepository.findById(id);
        return optional.map(order -> new ResponseEntity<>(order, HttpStatus.OK))
                       .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<Orders> save(Orders model) {
        try {
            Orders savedOrder = orderRepository.save(model);
            return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> deleteById(Integer id) {
        try {
            orderRepository.deleteById(id);
            return new ResponseEntity<>("Deleted successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Page<Orders>> findPaginated(Pageable pageable) {
        try {
            Page<Orders> ordersPage = orderRepository.findAll(pageable);
            if (ordersPage.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(ordersPage, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<ListOrderViewModel>> findByIdUser(Integer idUser) {
        try {
            List<Orders> orders = orderRepository.findByUserIdOrderByOrderId(idUser);
            
            if (orders.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            List<ListOrderViewModel> listOrderViewModels = orders.stream().map(order -> {
                List<OrderDetail> orderDetails = orderDetailRepository.findByOrder_IdOrder(order.getIdOrder());
                return convertToListOrderViewModel(order, orderDetails);
            }).collect(Collectors.toList());

            return new ResponseEntity<>(listOrderViewModels, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    private ListOrderViewModel convertToListOrderViewModel(Orders order, List<OrderDetail> orderDetails) {
        ListOrderViewModel viewModel = new ListOrderViewModel();
        viewModel.idOrder = order.getIdOrder();
        viewModel.thanhToan = order.getThanhToan();
        viewModel.appUser = order.getAppUser();
        viewModel.orderDate = order.getOrderDate();
        viewModel.status = order.getStatus();
        viewModel.phoneNumber = order.getPhoneNumber();
        viewModel.address = order.getAddress();
        viewModel.total = calculateTotal(orderDetails);
        return viewModel;
    }

    private Float calculateTotal(List<OrderDetail> orderDetails) {
        return orderDetails.stream()
                .map(detail -> detail.getPrice() * detail.getQuantity())
                .reduce(0.0f, Float::sum);
    }

	@Override
	public ResponseEntity<String> updateStatusOrder(UpdateStatusOrder model) {
		Orders or = orderRepository.findByIdOrder(model.idOrder);
		if(or==null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		Status st = new Status();
		st.setIdStatus(model.idStatus);
		or.setStatus(st);
		orderRepository.save(or);
		
		return new ResponseEntity<>("Cập nhật thành công", HttpStatus.OK);
	}
}
