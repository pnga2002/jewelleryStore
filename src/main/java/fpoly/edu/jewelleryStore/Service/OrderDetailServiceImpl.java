package fpoly.edu.jewelleryStore.Service;

import fpoly.edu.jewelleryStore.Entity.AppUser;
import fpoly.edu.jewelleryStore.Entity.OrderDetail;
import fpoly.edu.jewelleryStore.Entity.Orders;
import fpoly.edu.jewelleryStore.Entity.Product;
import fpoly.edu.jewelleryStore.Entity.Status;
import fpoly.edu.jewelleryStore.EntityViewModel.OrderViewModel;
import fpoly.edu.jewelleryStore.EntityViewModel.ThanhToanViewModel;
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
import java.util.Map;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.TreeMap;

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
		return optionalOrderDetail.map(orderDetail -> new ResponseEntity<>(orderDetail, HttpStatus.OK))
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
		order.setThanhToan(model.thanhToan);
		Orders newOrder = orderRepository.save(order);
		// duyệt vòng lặp cho list<Product> trong model.product để tạo orderdetail
		for (OrderViewModel.ProductQuantity productQuantity : model.product) {
			System.out.println("product id" + productQuantity.productId);

			System.out.println("product id" + productQuantity.price);
			Product optionalProduct = productRepository.findByIdProduct(productQuantity.productId);
			System.out.println(optionalProduct);
			if (optionalProduct != null) {
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setOrder(newOrder);
				orderDetail.setProduct(optionalProduct);

				orderDetail.setQuantity(productQuantity.quantity);

				orderDetail.setPrice(productQuantity.price);
				orderDetailRepository.save(orderDetail);
				cartDetailRepository.deleteById(productQuantity.idCartDetail);
			} else {
				return new ResponseEntity<>("Product with ID " + productQuantity.productId + " not found",
						HttpStatus.BAD_REQUEST);
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

	@Override
	public ResponseEntity<String> payPackage(ThanhToanViewModel model) throws IOException {
		try {
			String uri = merchantSendRequestDynamic(model);
			HttpURLConnection connection = (HttpURLConnection) new URL(uri).openConnection();

			connection.setRequestMethod("GET");

			connection.setInstanceFollowRedirects(false);

			String locationHeader = connection.getHeaderField("Location");
			connection.disconnect();

			return new ResponseEntity<>(locationHeader, HttpStatus.OK);
		} catch (ProtocolException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	public String merchantSendRequestDynamic(ThanhToanViewModel model) {
		AppUser user = userRepository.findByIdUser(model.order.idUser);
		String prefixHost = "https://mtf.onepay.vn/paygate/vpcpay.op?";
//        String prefixHost = "https://onepay.vn/paygate/vpcpay.op?";
		long ticks = new Date().getTime();
		String vpcMerchantTxnRef = Long.toString(ticks);
		Map<String, String> merchantParams = new HashMap<>();
		merchantParams.put("vpc_Version", "2");
		merchantParams.put("vpc_Currency", "VND");
		merchantParams.put("vpc_Command", "pay");
		merchantParams.put("vpc_AccessCode", "6BEB2546");
		merchantParams.put("vpc_Merchant", "TESTONEPAY");
		merchantParams.put("vpc_Locale", "vn");
		merchantParams.put("vpc_ReturnURL", model.urlAgain + "/success");
		merchantParams.put("vpc_MerchTxnRef", vpcMerchantTxnRef);
		merchantParams.put("vpc_OrderInfo", model.order.phoneNumber);
		merchantParams.put("vpc_Amount", String.valueOf(model.price));
		merchantParams.put("vpc_TicketNo", model.ticketNo);
		merchantParams.put("AgainLink", model.urlAgain);
		merchantParams.put("Title", "Codezuni");
		merchantParams.put("vpc_Customer_Phone", model.order.phoneNumber);
		merchantParams.put("vpc_Customer_Email", user.getEmail());
		merchantParams.put("vpc_Customer_Id", model.order.idUser + "");

		Map<String, String> dictSorted = sortParamsMap(merchantParams);
		String stringToHash = generateStringToHash(dictSorted);
		String sign = genSecureHash(stringToHash, "6D0870CDE5F24F34F3915FB0045120DB");
		StringBuilder queryParam = new StringBuilder();
		for (Map.Entry<String, String> items : merchantParams.entrySet()) {
			String key = items.getKey();
			String value = items.getValue();
			queryParam.append(key).append("=").append(value).append("&");
		}
		String requetsUrl = prefixHost + queryParam + "vpc_SecureHash=" + sign;
		System.out.println("requetsUrl " + requetsUrl);
		return requetsUrl;
	}

	public static String generateStringToHash(Map<String, String> dictSorted) {
		StringBuilder notEncodeData = new StringBuilder();

		for (Map.Entry<String, String> items : dictSorted.entrySet()) {
			String key = items.getKey();
			String value = items.getValue();
			String pref4 = key.length() >= 4 ? key.substring(0, 4) : "";
			String pref5 = key.length() >= 5 ? key.substring(0, 5) : "";

			if (pref4.equals("vpc_") || pref5.equals("user_")) {
				if (!key.equals("vpc_SecureHashType") && !key.equals("vpc_SecureHash")) {
					if (value.length() > 0) {
						if (notEncodeData.length() > 0) {
							notEncodeData.append("&");
						}
						notEncodeData.append(key).append("=").append(value);
					}
				}
			}
		}

		return notEncodeData.toString();
	}

	public static byte[] hexToBytes(String hex) {
		int len = hex.length();
		byte[] bytes = new byte[len / 2];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) ((Character.digit(hex.charAt(i * 2), 16) << 4)
					+ Character.digit(hex.charAt(i * 2 + 1), 16));
		}
		return bytes;
	}

	public static String genSecureHash(String notEncodeData, String merchantHashCode) {
		try {
			byte[] keyHash = hexToBytes(merchantHashCode);
			Mac hmac = Mac.getInstance("HmacSHA256");
			SecretKeySpec secretKeySpec = new SecretKeySpec(keyHash, "HmacSHA256");
			hmac.init(secretKeySpec);

			byte[] inputBytes = notEncodeData.getBytes(StandardCharsets.UTF_8);
			byte[] hashBytes = hmac.doFinal(inputBytes);

			StringBuilder sign = new StringBuilder();
			for (byte b : hashBytes) {
				sign.append(String.format("%02X", b));
			}
			return sign.toString();
		} catch (Exception e) {
			throw new RuntimeException("Error while generating secure hash", e);
		}
	}

	public static Map<String, String> sortParamsMap(Map<String, String> paramsMap) {
		return new TreeMap<>(paramsMap);
	}

}
