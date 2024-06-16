package fpoly.edu.jewelleryStore.Service;

import fpoly.edu.jewelleryStore.Entity.AppUser;
import fpoly.edu.jewelleryStore.Entity.OrderDetail;
import fpoly.edu.jewelleryStore.Entity.Orders;
import fpoly.edu.jewelleryStore.Entity.Product;
import fpoly.edu.jewelleryStore.Entity.Status;
import fpoly.edu.jewelleryStore.EntityViewModel.OrderViewModel;
import fpoly.edu.jewelleryStore.Repository.AppUserRepository;
import fpoly.edu.jewelleryStore.Repository.CartDetailRepository;
import fpoly.edu.jewelleryStore.Repository.OrderDetailRepository;
import fpoly.edu.jewelleryStore.Repository.OrderRepository;
import fpoly.edu.jewelleryStore.Repository.ProductRepository;
import fpoly.edu.jewelleryStore.Repository.StatusRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private AppUserRepository userRepository;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartDetailRepository cartDetailRepository;
    @Override
    public ResponseEntity<List<OrderDetail>> findAll() {
        List<OrderDetail> orderDetails = orderDetailRepository.findAll();
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OrderDetail> findById(Integer id) {
        Optional<OrderDetail> optionalOrderDetail = orderDetailRepository.findById(id);
        return optionalOrderDetail
                .map(orderDetail -> new ResponseEntity<>(orderDetail, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<String> save(OrderViewModel model) {
    	AppUser user = userRepository.findByIdUser(model.idUser);
    	Status status = new Status();
    	status.setIdStatus(1);
    	status.setName("Pending");
    	if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    	Orders order = new Orders();
    	order.setAddress(model.address);
    	order.setAppUser(user);
    	order.setOrderDate(new Date());
    	order.setStatus(status);
    	order.setPhoneNumber(model.phoneNumber);
    	order.setAddress(model.address);
    	Orders newOrder = orderRepository.save(order);
    	//duyệt vòng lặp cho  list<Product> trong model.product để tạo orderdetail
    	 for (OrderViewModel.ProductQuantity productQuantity : model.product) {
    		 System.out.println("product id"+  productQuantity.productId);

    		 System.out.println("product id"+  productQuantity.price);
             Product optionalProduct = productRepository.findByIdProduct(productQuantity.productId);
             System.out.println(optionalProduct);
             if (optionalProduct!=null) {
                 OrderDetail orderDetail = new OrderDetail();
                 orderDetail.setOrder(newOrder);
                 orderDetail.setProduct(optionalProduct);
                 
                 orderDetail.setQuantity(productQuantity.quantity);

                 orderDetail.setPrice(productQuantity.price);
                 orderDetailRepository.save(orderDetail);
                 cartDetailRepository.deleteById(productQuantity.idCartDetail);
             } else {
                 return new ResponseEntity<>("Product with ID " + productQuantity.productId + " not found", HttpStatus.BAD_REQUEST);
             }
         }
        String res = "Tạo thành công";
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteById(Integer id) {
        if (orderDetailRepository.existsById(id)) {
            orderDetailRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Page<OrderDetail>> findPaginated(Pageable pageable) {
        Page<OrderDetail> orderDetails = orderDetailRepository.findAll(pageable);
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }

	@Override
	public ResponseEntity<List<OrderDetail>> findByOrderId(Integer id) {
		List<OrderDetail> lst = orderDetailRepository.findByOrder_IdOrder(id);
		return new ResponseEntity<>(lst, HttpStatus.OK);
	}
}
