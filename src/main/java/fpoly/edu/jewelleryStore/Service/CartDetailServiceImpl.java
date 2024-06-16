package fpoly.edu.jewelleryStore.Service;

import fpoly.edu.jewelleryStore.Entity.AddCartVM;
import fpoly.edu.jewelleryStore.Entity.AppUser;
import fpoly.edu.jewelleryStore.Entity.CartDetail;
import fpoly.edu.jewelleryStore.Entity.Carts;
import fpoly.edu.jewelleryStore.Entity.Product;
import fpoly.edu.jewelleryStore.Repository.AppUserRepository;
import fpoly.edu.jewelleryStore.Repository.CartDetailRepository;
import fpoly.edu.jewelleryStore.Repository.CartRepository;
import fpoly.edu.jewelleryStore.Repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartDetailServiceImpl implements CartDetailService {

    @Autowired
    private CartDetailRepository cartDetailRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private AppUserRepository userRepository;
    @Autowired
    private ProductRepository prodRepository;
    @Override
    public ResponseEntity<List<CartDetail>> findAll() {
        List<CartDetail> cartDetails = cartDetailRepository.findAll();
        return new ResponseEntity<>(cartDetails, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CartDetail> findById(Integer id) {
        Optional<CartDetail> optionalCartDetail = cartDetailRepository.findById(id);
        return optionalCartDetail
                .map(cartDetail -> new ResponseEntity<>(cartDetail, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<CartDetail> save(AddCartVM model) {
    	 Optional<Carts> optionalCart = cartRepository.findByAppUser_IdUser(model.userId);
         Carts cart;
         if (optionalCart.isEmpty()) {
             // If no cart exists, create a new one
             AppUser user = userRepository.findByIdUser(model.userId);
             if (user == null) {
                 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
             }
             cart = new Carts();
             cart.setAppUser(user);
             cart.setDateCreate(new Date());
             cart = cartRepository.save(cart); // Save and get the cart with ID
         } else {
             cart = optionalCart.get();
         }
    	// Create a new cart detail and associate it with the cart
    	Product prod = prodRepository.findByIdProduct(model.productId);
    	 // Check if a CartDetail with the same productId already exists
        Optional<CartDetail> optionalCartDetail = cartDetailRepository.findByCart_IdCartAndProduct_IdProduct(cart.getIdCart(), model.productId);

        CartDetail cartDetail;
        if (optionalCartDetail.isPresent()) {
            // If it exists, update the quantity
            cartDetail = optionalCartDetail.get();
            cartDetail.setQuantity(cartDetail.getQuantity() + model.quantity);
        } else {
            // If it doesn't exist, create a new CartDetail
            cartDetail = new CartDetail();
            cartDetail.setCart(cart); // Set the cart reference
            cartDetail.setProduct(prod); // Assuming product exists
            cartDetail.setQuantity(model.quantity);
        }

        // Save the cart detail
        CartDetail savedCartDetail = cartDetailRepository.save(cartDetail);

        // Return the saved cart detail
        return new ResponseEntity<>(savedCartDetail, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteById(Integer id) {
        if (cartDetailRepository.existsById(id)) {
            cartDetailRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<CartDetail>> findByUserId(Integer userId) {
        List<CartDetail> cartDetails = cartDetailRepository.findByCart_AppUser_IdUser(userId);
        return new ResponseEntity<>(cartDetails, HttpStatus.OK);
    }
}
