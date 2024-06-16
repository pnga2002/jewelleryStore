package fpoly.edu.jewelleryStore.Service;

import fpoly.edu.jewelleryStore.Entity.Carts;
import fpoly.edu.jewelleryStore.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartsRepository;

    @Override
    public ResponseEntity<List<Carts>> findAll() {
        List<Carts> carts = cartsRepository.findAll();
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Carts> findById(Integer id) {
        Optional<Carts> optionalCart = cartsRepository.findById(id);
        return optionalCart
                .map(cart -> new ResponseEntity<>(cart, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<Carts> save(Carts cart) {
        Carts savedCart = cartsRepository.save(cart);
        return new ResponseEntity<>(savedCart, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteById(Integer id) {
        if (cartsRepository.existsById(id)) {
            cartsRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
