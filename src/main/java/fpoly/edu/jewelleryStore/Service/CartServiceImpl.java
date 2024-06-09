package fpoly.edu.jewelleryStore.Service;

import fpoly.edu.jewelleryStore.Entity.Carts;
import fpoly.edu.jewelleryStore.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartsRepository;

    @Override
    public List<Carts> findAll() {
        return cartsRepository.findAll();
    }

    @Override
    public Carts findById(Integer id) {
        Optional<Carts> optionalCart = cartsRepository.findById(id);
        return optionalCart.orElse(null);
    }

    @Override
    public Carts save(Carts cart) {
        return cartsRepository.save(cart);
    }

    @Override
    public void deleteById(Integer id) {
        cartsRepository.deleteById(id);
    }


}
