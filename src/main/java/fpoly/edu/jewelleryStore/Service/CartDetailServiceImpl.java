package fpoly.edu.jewelleryStore.Service;

import fpoly.edu.jewelleryStore.Entity.CartDetail;
import fpoly.edu.jewelleryStore.Repository.CartDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartDetailServiceImpl implements CartDetailService {

    @Autowired
    private CartDetailRepository cartDetailRepository;

    @Override
    public List<CartDetail> findAll() {
        return cartDetailRepository.findAll();
    }

    @Override
    public CartDetail findById(Integer id) {
        Optional<CartDetail> optionalCartDetail = cartDetailRepository.findById(id);
        return optionalCartDetail.orElse(null);
    }

    @Override
    public CartDetail save(CartDetail cartDetail) {
        return cartDetailRepository.save(cartDetail);
    }

    @Override
    public void deleteById(Integer id) {
        cartDetailRepository.deleteById(id);
    }

    @Override
    public List<CartDetail> findByCartIdAndUserId(Integer cartId, Integer userId) {
        return cartDetailRepository.findByCart_IdAndCart_AppUser_IdUser(cartId, userId);
    }
}
